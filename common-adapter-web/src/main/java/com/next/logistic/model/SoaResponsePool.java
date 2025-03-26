package com.next.logistic.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SoaResponsePool {

    private static final Map<String, SoaResponse> responseMap = new ConcurrentHashMap<>();

    public static void put(String soaCode) {

        responseMap.put(soaCode, new SoaResponse(soaCode));
    }

    public static SoaResponse get(String soaCode) {
        log.debug("Start get soa-code {}", soaCode);
        if(soaCode == null) {
            log.debug("soaCode is null");
            return null;
        }

        if(soaCode.startsWith(SoaResponse.PREFIX_MESSAGE_CODE)) {
            String messageCode = soaCode.substring(SoaResponse.PREFIX_MESSAGE_CODE.length());
            SoaResponse response =  responseMap.get(messageCode);
            log.debug("Soa response code {}, {}",soaCode, response.toString());
            return response;
        }
        SoaResponse soaResponse = responseMap.get(soaCode);
        log.debug("Soa response code {}, {}",soaCode, soaResponse.toString());
        return soaResponse;
    }
}
