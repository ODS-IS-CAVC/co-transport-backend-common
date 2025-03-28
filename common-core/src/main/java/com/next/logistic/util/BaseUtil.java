package com.next.logistic.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BaseUtil {

    private static final int SHIFT = 3;
    static Map<String,String> map = new HashMap<>();

    private BaseUtil(){
        map.put("9999900010015", "4149999900010015");
        map.put("9999900010022", "4149999900010022");
    }

    public static List<String> stringToList(String input, String character){

        if (isNull(input) || isNull(character)){
            return new ArrayList<>();
        }
        return List.of(input.split(character));
    }

    public static String listToString(List<String> input, String character){

        if (isEmpty(input, null, 1) || isNull(character)){
            return null;
        }
        return String.join(character, input);
    }

    public static boolean isEmpty(List<?> list, Map<?,?> map, int type){
        boolean status = false;
        switch (type){
            case 1:
                if (list == null || list.isEmpty()){
                    status = true;
                }
                break;
            case 2:
                if (map == null || map.isEmpty()){
                    status = true;
                }
                break;
            default:
                break;
        }
        return status;
    }

    public static boolean isNull(String input){
        boolean status = false;
        if (input == null || input.isBlank() || "null".equals(input)){
            status = true;
        }
        return status;
    }

    public static <T> void copyProperties(T src, T target, String... ignoreProperties){

        BeanUtils.copyProperties(src, target, ignoreProperties);
    }

    public static <T> String[] getNullPropertiesNames(T source){

        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null){
                emptyNames.add(pd.getName());
            }
        }

        String[] results = new String[emptyNames.size()];
        return emptyNames.toArray(results);
    }

    public static <T> void copyPropertiesIgnoreNullField(T src, T target, String... ignoreProperties){

        Set<String> emptyNames = new HashSet<>(Arrays.asList(ignoreProperties));
        emptyNames.addAll(List.of(getNullPropertiesNames(src)));
        String[] results = new String[emptyNames.size()];

        BeanUtils.copyProperties(src, target, emptyNames.toArray(results));
    }

    /**
     *
     * @param input string need encode
     * @return string encoded
     */
    private static final String SECRET_KEY = "1234567890123456"; // 16 byte key
    private static final String INIT_VECTOR = "InitializationVe"; // 16 byte IV
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5PADDING";

    public static String encrypt(String email) {
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec ivParams = new IvParameterSpec(INIT_VECTOR.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
            byte[] encrypted = cipher.doFinal(email.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        }catch (Exception e){
            return null;
        }
    }

    public static String decrypt(String hashedEmail) {
        StringBuilder hashedEmailBuilder = new StringBuilder(hashedEmail.replaceAll("[^A-Za-z0-9+/=]", ""));// Remove invalid characters
        while (hashedEmailBuilder.length() % 4 != 0) {
            hashedEmailBuilder.append("="); // Padding
        }
        hashedEmail = hashedEmailBuilder.toString();

        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            IvParameterSpec ivParams = new IvParameterSpec(INIT_VECTOR.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(hashedEmail));
            return new String(decrypted);
        }catch (Exception e){
            return null;
        }
    }

    public static String encodeURL(String input){

        return URLEncoder.encode(input, StandardCharsets.UTF_8);
    }

    public static String decodeURL(String input){

        return URLDecoder.decode(input, StandardCharsets.UTF_8);
    }

    public static String dateToString(Instant instant){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                .withZone(ZoneId.systemDefault()); // UTCに設定

        return formatter.format(instant);
    }

    // 指定された長さのランダムパスワードを生成する関数
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
    public static String generatePassword(int length) {
        StringBuilder password = new StringBuilder(length);
        SecureRandom random = new SecureRandom();

        // 指定された長さのパスワードを生成
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }
        return password.toString();
    }

    public static boolean isUUID(String uuid) {
        try {
            UUID.fromString(uuid);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static UriComponentsBuilder convertURL(String domain, Map<String,Object> params){

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(domain);
        if (!BaseUtil.isEmpty(null, params, 2)){
            Set<String> keySet = params.keySet();
            keySet.stream().forEach(key -> {
                Object value = params.get(key);
                if (Objects.nonNull(value)){
                    builder.queryParam(key, value);
                }
            });
        }
        return builder;
    }

    public static String formatTime(Object input) {
        if (input == null) {
            return "";
        }
        String timeStr = input.toString();
        if (timeStr.contains(":")) {
            timeStr =  timeStr.replace(":", "");
        }
        if (timeStr.length() == 4) {
            return timeStr.substring(0, 2) + ":" + timeStr.substring(2, 4) + ":00";
        }
        if (timeStr.length() == 6) {
            return timeStr.substring(0, 2) + ":" + timeStr.substring(2, 4) + ":" + timeStr.substring(4, 6);
        }
        return timeStr;
    }

    public static String generateId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = sdf.format(new Date());
        int randomNum = new Random().nextInt(900) + 100;
        return timestamp + randomNum;
    }

    public static String convertLocalDateToString(LocalDate date) {
        if (date == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return date.format(formatter);
    }

    public static String makeString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getGiai(String giaiNumber) {
        if(giaiNumber == null || giaiNumber.length() == 0) {
            return "8004991000001000000000000000000100,8004991000002000000000000000000022";
        }
        return giaiNumber;
    }

    public static String getGiaiList(String giaiNumber) {
        if(giaiNumber == null || giaiNumber.length() == 0) {
            return "8004991000001000000000000000000100,8004991000002000000000000000000022";
        }
        return giaiNumber;
    }

    public static String getTrailerGiaList(List<String> giaList) {
        return giaList != null && giaList.size() > 0 ?  String.join(",", giaList)
                : "8004991000001000000000000000000100,8004991000002000000000000000000022";
    }

    public static String getTrailerGiaList(String giaList) {
        return giaList != null && giaList.length() > 0 ? giaList : "8004991000001000000000000000000100,8004991000002000000000000000000022";
    }
    public static String getTractorGiaList(String giaList) {
        return giaList != null && giaList.length() > 0 ? giaList : "8004991000002000000000000000000001";
    }

    public static String getShipperTractorList(String shipper) {
//        Map<String, String> mapping = new HashMap<>();
//        mapping.put("992000001", "8004992000001000000000000000000001");
//        mapping.put("992000002", "8004992000002000000000000000000001");
//        mapping.put("992000003", "8004992000003000000000000000000001");
//        return mapping.getOrDefault(shipper, "8004992000002000000000000000000001");
        return "8004992000001000000000000000000001,8004992000002000000000000000000001";
    }

    public static String getReceiverTractorList(String shipper) {
        return "8004993000001000000000000000000001,8004993000002000000000000000000001";
    }
    public static String getShipperGiaiList(String shipper) {
//        Map<String, String> mapping = new HashMap<>();
//        mapping.put("992000001", "8004992000001000000000000000000003,8004992000001000000000000000000004");
//        mapping.put("992000002", "8004992000002000000000000000000003,8004992000002000000000000000000004");
//        mapping.put("992000003", "8004992000003000000000000000000002,8004992000003000000000000000000003");
        return "8004992000001000000000000000000003,8004992000002000000000000000000003";
    }

    public static String getCarrierTractorGiaList(String carrier) {
        return "991000001".equals(carrier)  ? "8004991000001000000000000000000001"
                :  "8004991000002000000000000000000001"; //TODO: ymt or NLJ
    }

    public static String getGlnStart(String glnStart) {

        if(map.get(glnStart) == null) {
            return "4149999900010015";
        }

        return map.get(glnStart);
    }

    public static String getGlnEnd(String glnEnd) {
        if(map.get(glnEnd) == null) {
            return "4149999900010022";
        }
        return map.get(glnEnd);
    }

    public static String getCarrierCid(String cid) {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("991000001", "991000001");
        mapping.put("991000002", "991000002");
        mapping.put("991000003", "991000002");
        return mapping.getOrDefault(cid, "991000001");
    }

    public static String getShipperCid(String cid) {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("992000001", "992000001");
        mapping.put("992000002", "992000002");
        mapping.put("992000003", "992000003");
        return mapping.getOrDefault(cid, "992000001");
    }

    public static String getMhSpaceList(List<String> mhSpaceList, boolean isDeparture) {
        String result = isDeparture ? "A023,B021" : "C024,D022";
        return mhSpaceList != null && mhSpaceList.size() > 0 ?  String.join(",", mhSpaceList) : result;
    }

    public static String getGlnFrom(String glnFrom) {
        if ("9999900010015".equals(glnFrom) || "9999900010022".equals(glnFrom)) {
            return glnFrom;
        }
        return "9999900010015";
    }

    public static String getGlnTo(String glnTo) {
        if ("9999900010015".equals(glnTo) || "9999900010022".equals(glnTo)) {
            return glnTo;
        }
        return "9999900010022";
    }

    public static final String YAMATO_DEFAULT_FROM = "9999900010035";
    public static final String YAMATO_DEFAULT_TO = "9999900010052";

    public static String getGlnDefaultYamato(String code, Boolean isFrom) {
        if (BaseUtil.isNull(code)) {
            return isFrom ? YAMATO_DEFAULT_FROM : YAMATO_DEFAULT_TO;
        }
        if (code.contains(YAMATO_DEFAULT_FROM) || code.contains(YAMATO_DEFAULT_TO)) {
            return code;
        } else {
            return isFrom ? YAMATO_DEFAULT_FROM : YAMATO_DEFAULT_TO;
        }
    }


}
