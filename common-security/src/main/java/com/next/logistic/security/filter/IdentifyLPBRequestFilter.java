package com.next.logistic.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

import static com.next.logistic.util.Constants.XHTTP.ATTRIBUTE_REQUEST_ID;
import static com.next.logistic.util.WrapperUtil.wrapRequest;
import static com.next.logistic.util.WrapperUtil.wrapResponse;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class IdentifyLPBRequestFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        ContentCachingRequestWrapper bufferedRequest = wrapRequest((HttpServletRequest) request);
        ContentCachingResponseWrapper bufferedResponse = wrapResponse(
                (HttpServletResponse) response);

        //Set request Id
        String xid = UUID.randomUUID().toString();
        String requestId = this.getRequestId(bufferedRequest, xid);

        try {

            if (MDC.get(ATTRIBUTE_REQUEST_ID) == null) {
                MDC.put(ATTRIBUTE_REQUEST_ID, requestId);
            }

            chain.doFilter(bufferedRequest, bufferedResponse);
        } finally {
            MDC.remove(ATTRIBUTE_REQUEST_ID);
            this.identify(requestId, bufferedResponse);
            bufferedResponse.copyBodyToResponse();
        }

    }

    protected String getRequestId(HttpServletRequest request, String defaultId) {
        String requestId = request.getHeader(ATTRIBUTE_REQUEST_ID);
        return requestId == null ? defaultId : requestId;
    }

    public void identify(String requestId,
                         ContentCachingResponseWrapper responseWrapper) {
        responseWrapper.setHeader(ATTRIBUTE_REQUEST_ID, requestId);

    }
}
