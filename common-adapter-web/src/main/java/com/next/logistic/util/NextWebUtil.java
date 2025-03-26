package com.next.logistic.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import com.next.logistic.exception.NextSystemException;
import com.next.logistic.exception.NextTokenException;
import com.next.logistic.exception.NextUserServiceException;
import com.next.logistic.exception.NextWebException;
import com.next.logistic.exception.model.NextAPIError;
import com.next.logistic.model.SoaResponsePool;
import com.next.logistic.model.SystemLogin;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import jakarta.validation.constraints.NotNull;

@Slf4j
public class NextWebUtil {

    private static final ObjectMapper mapper = ObjectMapperUtil.getInstance();

    private NextWebUtil(){}

    public static final String CACHE_M2M_TOKEN_KEY = "m2m_token_key";
    public static final long EXPIRATION_TIME = 1000 * 60 * 120;

    /**
     * This method supports check token is expired for jwt format.
     *
     * @param tokenJwt
     * @return
     * @throws InvalidJwtException
     * @throws MalformedClaimException
     */
    public static boolean isExpired(String tokenJwt)
            throws InvalidJwtException, MalformedClaimException {
        log.debug("Begin checking this token: {}, is expired at current time: {}", tokenJwt,
                new Date());
        JwtConsumer consumer = new JwtConsumerBuilder().setSkipAllValidators()
                .setDisableRequireSignature()
                .setSkipSignatureVerification().build();
        JwtClaims claims = consumer.processToClaims(tokenJwt);
        NumericDate expirationTime = claims.getExpirationTime();
        boolean isExpired = NumericDate.now().isAfter(expirationTime);
        log.debug("Token expired is: {}", isExpired);
        return isExpired;
    }

    public static <T> T convertFromJson(String json, Class<T> t) throws JsonProcessingException {
        log.debug("Begin converting json to {}: {}", t.getName(), json);
        ObjectMapper objectMapper = ObjectMapperUtil.getInstance();

        return objectMapper.readValue(json, t);
    }

    public static <T> List<T> transformToDTO(List json, Class<T> t) {
        return mapper.convertValue(json, new TypeReference<List<T>>() {
        });
    }

    public static <T> T transformToDTO(String jsonStr, TypeReference<T> transType) {
        if (!StringUtils.hasText(jsonStr)) {
            return null;
        } else {
            try {
                return mapper.readValue(jsonStr, transType);
            } catch (Exception e) {
                if (log.isErrorEnabled()) {
                    log.error("Json is bad format.", e);
                }

            }
        }
        return null;
    }

    /**
     * to Save binary data to local with filename in folderpath, support create folder if not
     * exist.
     *
     * @param data
     * @param folderPath
     * @param fileName
     * @throws NextSystemException
     */
    public static void saveFileToLocal(byte[] data, @NotNull String folderPath,
                                       @NotNull String fileName) throws NextSystemException {

        Assert.isTrue(data != null, "bad file");
        String fullFilePath =
                StringUtils.endsWithIgnoreCase(folderPath, "/") ? folderPath + fileName
                        : folderPath + "/" + fileName;
        Path path = Paths.get(fullFilePath);

        try {
            Files.createDirectories(Paths.get(folderPath));
            Files.write(path, data);

        } catch (IOException e) {
            log.error("an encounter error during write file", e);
            throw new NextSystemException(NextError.SYSTEM_FILE_WRITE_EXCEPTION);
        }
    }

    /**
     * Support removing folder and all sub-folders
     *
     * @param fullFolderPath
     * @throws IOException
     */
    public static void removeFolder(String fullFolderPath) throws IOException {
        FileUtils.deleteDirectory(new File(fullFolderPath));

    }

    /**
     * Support removing file.
     *
     * @param fullFilePath
     * @throws IOException
     */
    public static void deleteFile(String fullFilePath) throws IOException {
        FileUtils.forceDelete(new File(fullFilePath));
    }

    public static String encryptSystem(SystemLogin systemLogin, String secretKey){

        try {
            String json = mapper.writeValueAsString(systemLogin);

            Map<String, Object> claims = new HashMap<>();
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

            JwtBuilder builder = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(json)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, secretKey);
            return builder.compact();
        } catch (Exception e) {
            throw new NextTokenException(NextError.SYSTEM_ENCRYPT_TOKEN_EXCEPTION, e);
        }
    }

    public static String generateRefreshToken(String clientId, String secretKey){

        Map<String, Object> claims = new HashMap<>();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
        try {
            JwtBuilder builder = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(clientId)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, secretKey);
            return builder.compact();
        }catch (Exception e){
            throw new NextTokenException(NextError.SYSTEM_GENERATE_TOKEN_EXCEPTION, e);
        }
    }

    public static String decryptRefreshToken(String refreshToken, String publicKeyBase64){

        // Phân tích JWT và lấy Claims
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKeyBase64)
                    .build()
                    .parseClaimsJws(refreshToken)
                    .getBody();

            return claims.getSubject();
        }catch (Exception e){
            throw new NextTokenException(NextError.SYSTEM_VERIFY_TOKEN_EXCEPTION, e);
        }
    }

    public static SystemLogin decrypt(String jwt, String publicKeyBase64) {
        try {
            // Phân tích JWT và lấy Claims
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(publicKeyBase64)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            // Chuyển đổi Claims thành đối tượng SystemLogin
            String json = claims.getSubject();
            return mapper.readValue(json, SystemLogin.class);
        } catch (Exception e) {

            throw new NextTokenException(NextError.SYSTEM_DECRYPT_TOKEN_EXCEPTION, e);
        }
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 20; // Độ dài của SECRET_KEY

    public static String generateUniqueString(String domain) {
        // Hash domain để có chuỗi cố định
        String hash = hashDomain(domain);

        // Chọn 10 ký tự đầu tiên từ hash và chuyển đổi thành chuỗi unique
        return convertHashToUniqueString(hash);
    }

    public static String generateSecretKey(String domain){
        String secretKeyResponse = null;
        try {
            SecureRandom secureRandom = new SecureRandom();

            byte[] randomBytes = new byte[32]; // 32 bytes (256 bits)
            secureRandom.nextBytes(randomBytes);

            byte[] textBytes = domain.getBytes(StandardCharsets.UTF_8);

            byte[] combinedBytes = new byte[randomBytes.length + textBytes.length];
            System.arraycopy(randomBytes, 0, combinedBytes, 0, randomBytes.length);
            System.arraycopy(textBytes, 0, combinedBytes, randomBytes.length, textBytes.length);

            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Use SHA-256 for 32-byte key
            byte[] hash = digest.digest(combinedBytes);

            SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(hash, 0, 32, "AES");

            secretKeyResponse = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return secretKeyResponse;
    }

    private static String hashDomain(String domain) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(domain.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new NextTokenException(NextError.SYSTEM_HASH_DOMAIN_EXCEPTION, e);
        }
    }

    private static String convertHashToUniqueString(String hash) {
        StringBuilder uniqueString = new StringBuilder();
        for (char c : hash.toCharArray()) {
            if (CHARACTERS.indexOf(c) != -1) {
                uniqueString.append(c);
            }
            if (uniqueString.length() == LENGTH) {
                break;
            }
        }
        // Nếu không đủ 10 ký tự từ hash, bổ sung bằng ký tự từ CHARSET
        while (uniqueString.length() < LENGTH) {
            int index = (int) (Math.random() * CHARACTERS.length());
            uniqueString.append(CHARACTERS.charAt(index));
        }
        return uniqueString.toString();
    }

    public static String extractDomainName(String url) {
        try {
            URI uri = new URI(url);
            String host = uri.getHost();
            // Loại bỏ 'www.' nếu có
            if (host != null && host.startsWith("www.")) {
                host = host.substring(4);
            }
            return host;
        } catch (URISyntaxException e) {
            throw new NextTokenException(NextError.SYSTEM_HASH_DOMAIN_EXCEPTION, e);
        }
    }

    public static PublicKey getPublicKey(String publicKeyPEM) throws Exception {
        // Loại bỏ các phần không cần thiết của chuỗi PEM
        String publicKeyContent = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        // Chuyển đổi chuỗi Base64 thành byte array
        byte[] decoded = Base64.getDecoder().decode(publicKeyContent);

        // Tạo đối tượng KeyFactory cho RSA
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);

        // Tạo đối tượng PublicKey
        return keyFactory.generatePublic(keySpec);
    }

    public static void throwCustomException(HttpStatus status, String code, String... params) {
        throw new NextWebException(new NextAPIError(
                status,
                SoaResponsePool.get(code),
                params
        ));
    }
}
