package com.next.logistic.exception;

import com.next.logistic.exception.model.ObjectError;
import com.next.logistic.model.*;
import com.next.logistic.util.BaseUtil;
import com.next.logistic.util.JHipsterConstants;
import com.next.logistic.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.StatusType;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.spring.web.advice.security.SecurityAdviceTrait;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static com.next.logistic.model.SoaResponse.PREFIX_MESSAGE_CODE;
import static com.next.logistic.util.Constants.XHTTP.ATTRIBUTE_REQUEST_ID;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class NextExceptionTranslator implements ProblemHandling, SecurityAdviceTrait {

    private final Environment env;

    private static final ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

    private final MessageSource messageSource;


    public NextExceptionTranslator(Environment env, MessageSource messageSource) {
        this.env = env;
        this.messageSource = messageSource;
    }

    /**
     * Post-process the Problem payload to add the message key for the front-end if needed.
     */
    @Override
    public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity,
                                           NativeWebRequest request) {
        if (entity == null) {
            return null;
        }
        Problem problem = entity.getBody();

        if (problem instanceof NextProblem) {
            return new ResponseEntity<>(problem, entity.getHeaders(), entity.getStatusCode());

        }
        ResponseError error = new ResponseError();
        ResponseData<Void> responseData = new ResponseData<>();
        error.setRequestId(MDC.get(ATTRIBUTE_REQUEST_ID));

        error.setStatus(entity.getStatusCodeValue());
        responseData.setData(null);

        if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
            if (problem != null) {
                error.setCode(SoaResponsePool.get("4000").getSoaErrorCode());
                error.setMessage(SoaResponsePool.get("4000").getSoaErrorMsg());
            }

            Map<String, Object> map = objectMapper
                    .convertValue(responseData, new TypeReference<>() {
                    });
            this.appendMessage(responseData);
            Problem problemResponse = new NextProblem(map);

            return new ResponseEntity<>(problemResponse, entity.getHeaders(), entity.getStatusCode());

        }
        if (problem instanceof ConstraintViolationProblem) {
            List<Violation> violations = ((ConstraintViolationProblem) problem).getViolations();
            List<ObjectError> objectErrors = new ArrayList<>();

            violations.forEach(violation -> {
                objectErrors.add(ObjectError.builder().field(violation.getField()).issue(
                        violation.getMessage()).build());
            });

            error.setCode(SoaResponsePool.get("4000").getSoaErrorCode());
            error.setMessage(SoaResponsePool.get("4000").getSoaErrorMsg());
            error.setDetails(objectErrors);

        } else {
            SoaResponse soaResponse = SoaResponsePool.get(
                    String.valueOf(entity.getStatusCodeValue()));
            if (soaResponse != null) {
                error.setCode(soaResponse.getSoaErrorCode());
                error.setMessage(soaResponse.getSoaErrorMsg());
            } else {
                error.setCode(SoaResponsePool.get("500").getSoaErrorCode());
                error.setMessage(SoaResponsePool.get("500").getSoaErrorMsg());
            }

        }
        error.setDateTime(BaseUtil.dateToString(Instant.now()));
        responseData.setError(error);

        this.appendMessage(responseData);
        Map<String, Object> map = objectMapper
                .convertValue(responseData, new TypeReference<>() {
                });

        Problem problemResponse = new NextProblem(map);
        return new ResponseEntity<>(problemResponse, entity.getHeaders(), entity.getStatusCodeValue());
    }

    @Override
    public ResponseEntity<Problem> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                @Nonnull NativeWebRequest request) {

        BindingResult result = ex.getBindingResult();

        List<ObjectError> fieldErrors = result.getFieldErrors().stream().map(
                f -> ObjectError.builder()
                        .field(f.getField()).issue(
                                StringUtils.isNotBlank(f.getDefaultMessage()) ? f.getDefaultMessage()
                                        : f.getCode()).build()

        ).collect(Collectors.toList());

        Problem problem = this.problemGenerator(
                SoaResponsePool.get("4000").getSoaErrorCode(),
                HttpStatus.BAD_REQUEST.value(), fieldErrors);

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler
    public ResponseEntity<Problem> handleConcurrencyFailure(ConcurrencyFailureException ex,
                                                            NativeWebRequest request) {

        Problem problem = this.problemGenerator(
                SoaResponsePool.get("4001").getSoaErrorCode(),
                HttpStatus.BAD_REQUEST.value(), null);

        return new ResponseEntity<>(problem, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ProblemBuilder prepare(final Throwable throwable, final StatusType status,
                                  final URI type) {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());

        if (activeProfiles.contains(JHipsterConstants.SPRING_PROFILE_PRODUCTION)) {
            if (throwable instanceof HttpMessageConversionException) {
                return Problem.builder().withType(type).withTitle(status.getReasonPhrase())
                        .withStatus(status).withDetail("Unable to convert http message").withCause(
                                Optional.ofNullable(throwable.getCause())
                                        .filter(cause -> isCausalChainsEnabled()).map(this::toProblem)
                                        .orElse(null));
            }
            if (throwable instanceof DataAccessException) {
                return Problem.builder().withType(type).withTitle(status.getReasonPhrase())
                        .withStatus(status).withDetail("Failure during data access").withCause(
                                Optional.ofNullable(throwable.getCause())
                                        .filter(cause -> isCausalChainsEnabled()).map(this::toProblem)
                                        .orElse(null));
            }
            if (containsPackageName(throwable.getMessage())) {
                return Problem.builder().withType(type).withTitle(status.getReasonPhrase())
                        .withStatus(status).withDetail("Unexpected runtime exception").withCause(
                                Optional.ofNullable(throwable.getCause())
                                        .filter(cause -> isCausalChainsEnabled()).map(this::toProblem)
                                        .orElse(null));
            }
        }

        return Problem.builder().withType(type).withTitle(status.getReasonPhrase())
                .withStatus(status).withDetail(throwable.getMessage()).withCause(
                        Optional.ofNullable(throwable.getCause()).filter(cause -> isCausalChainsEnabled())
                                .map(this::toProblem).orElse(null));
    }


    @ExceptionHandler({NextCoreException.class})
    public ResponseEntity<Problem> handleXCoreExceptionError(final NextCoreException ex,
                                                             final WebRequest request) {

        Problem problem = this.problemGenerator(
                SoaResponsePool.get("500").getSoaErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler({NextWebException.class})
    public ResponseEntity<Problem> handleXWebExceptionError(final NextWebException ex,
                                                            final WebRequest request) {

        Problem problem = this.problemGenerator(
                ex.getApiError().getSoaResponse().getSoaErrorCode(),
                ex.getApiError().getStatus().value(), ex.getApiError().getFieldError(),
                ex.getApiError().getParameters());

        return new ResponseEntity<>(problem, ex.getApiError().getStatus());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Problem> handleUnknownError(final Exception ex,
                                                      final WebRequest request) {

        Problem problem = this.problemGenerator(
                SoaResponsePool.get("500").getSoaErrorCode(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), null);

        return new ResponseEntity<>(problem, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean containsPackageName(String message) {
        // This list is for sure not complete
        return StringUtils.containsAny(message, "org.", "java.", "net.", "jakarta.", "com.", "io.",
                "de.", "next.");
    }

    private void appendMessage(ResponseData<?> responseData, Object... objects) {

        ResponseError error = responseData.getError();
        if (error == null || error.getCode() == null) {
            return;
        }
        error.setMessage(
                messageSource.getMessage(PREFIX_MESSAGE_CODE + error.getCode(), objects,
                        LocaleContextHolder.getLocale()));
        responseData.setError(error);
    }

    private Problem problemGenerator(String soaCode, int status, List<ObjectError> fieldErrors, Object... objects) {
        ResponseData<Void> responseData = new ResponseData<>();

        ResponseError error = new ResponseError();
        error.setCode(soaCode);
        error.setRequestId(MDC.get(ATTRIBUTE_REQUEST_ID));
        error.setStatus(status);
        error.setDetails(fieldErrors);
        responseData.setError(error);
        this.appendMessage(responseData, objects);
        responseData.setData(null);
        if (fieldErrors != null && !fieldErrors.isEmpty()) {
            error.setMessage(fieldErrors.get(0).getIssue());
        }
        error.setDateTime(BaseUtil.dateToString(Instant.now()));
        if (status == 200){
            responseData.setError(null);
        }
        responseData.setError(error);

        Map<String, Object> map = objectMapper
                .convertValue(responseData, new TypeReference<>() {
                });

        return new NextProblem(map);
    }
}
