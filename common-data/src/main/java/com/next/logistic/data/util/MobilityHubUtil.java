package com.next.logistic.data.util;

import com.next.logistic.data.config.CommonDataConfigProperties;
import com.next.logistic.data.dto.request.AttributeRequestDTO;
import com.next.logistic.data.dto.request.RequestDescriptionDTO;
import com.next.logistic.data.dto.request.ReservationRequestDTO;
import com.next.logistic.data.dto.request.StatusesRequestDTO;
import com.next.logistic.data.dto.response.ReservationResponseDTO;
import com.next.logistic.util.BaseUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * モビリティハブユーティリティクラス。
 *
 * @author Next Logistics
 */
@Service
public class MobilityHubUtil {

    private final String API_MOBILITY_HUB = "/api/v1/Resources";
    private static final int ERROR_CODE_0 = 1000; // Success
    private static final int ERROR_CODE_4 = 1004; // System error

    private static final Logger logger = LoggerFactory.getLogger(MobilityHubUtil.class);

    @Autowired
    private CommonDataConfigProperties commonDataConfigProperties;

    /**
     * モビリティハブを更新するメソッド。
     *
     * @param mobilityHubId モビリティハブID
     * @param freightId     貨物ID
     * @param truckId       トラックID
     * @param sizeClass     サイズクラス
     * @return エラーコード
     */
    public ReservationResponseDTO updateMobilityHub(List<String> mobilityHubId, List<String> freightId,
        List<String> truckId, List<String> sizeClass, List<LocalDateTime> validFrom, List<LocalDateTime> validUntil) {
        try {
            logger.info("START CALL DMP PUT MOBILITY-HUB");
            String url = commonDataConfigProperties.getDomainGateway() + API_MOBILITY_HUB;
            RestTemplate restTemplate = new RestTemplate();

            // RequestDescriptionDTO
            List<RequestDescriptionDTO> requestDescriptionDTOList = new ArrayList<>();
            for (int i = 0; i < mobilityHubId.size(); i++) {
                RequestDescriptionDTO requestDescriptionDTO = new RequestDescriptionDTO();
                requestDescriptionDTO.setMobilityHubId(mobilityHubId.get(i));
                requestDescriptionDTO.setFreightId(freightId.get(i));
                requestDescriptionDTO.setTruckId(truckId.get(i));
                requestDescriptionDTO.setSizeClass(sizeClass.get(i));
                requestDescriptionDTOList.add(requestDescriptionDTO);
            }
            // StatusesRequestDTO
            List<StatusesRequestDTO> statusesRequestDTOList = new ArrayList<>();
            for (int i = 0; i < mobilityHubId.size(); i++) {
                StatusesRequestDTO statusesRequestDTO = new StatusesRequestDTO();
                statusesRequestDTO.setRequestObject(requestDescriptionDTOList.get(i));
                statusesRequestDTO.setValidFrom(validFrom.get(i).toString());
                statusesRequestDTO.setValidUntil(validUntil.get(i).toString());
                statusesRequestDTOList.add(statusesRequestDTO);
            }
            // AttributeRequestDTO
            AttributeRequestDTO attributeRequestDTO = new AttributeRequestDTO();
            attributeRequestDTO.setStatuses(statusesRequestDTOList);
            attributeRequestDTO.setCategory("mobilityhub");
            // ReservationRequestDTO
            ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO();
            reservationRequestDTO.setDataModelType("test1");
            reservationRequestDTO.setAttribute(attributeRequestDTO);
            // Create HttpEntity
            HttpEntity<ReservationRequestDTO> entity = new HttpEntity<>(reservationRequestDTO);

            // Send request
            ResponseEntity<ReservationResponseDTO> response = restTemplate.exchange(url, HttpMethod.PUT, entity,
                ReservationResponseDTO.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("PUT MOBILITY-HUB Response body: {}", response.getBody());
                return response.getBody();
            }
        } catch (Exception e) {
            logger.error("EXCEPTION IN CALL MOBILITYHUB: {}", e.getMessage());
            return null;
        }

        return null;
    }

    /**
     * モビリティハブを削除するメソッド。
     *
     * @param reservationId 予約ID
     * @return エラーコード
     */
    public int deleteMobilityHub(String reservationId) {
        if (BaseUtil.isNull(reservationId)) {
            logger.error("DELETE MOBILITY-HUB reservationId is Null");
            return ERROR_CODE_4;
        }
        try {
            logger.info("START CALL DMP DELETE MOBILITY-HUB");
            String url = commonDataConfigProperties.getDomainGateway() + API_MOBILITY_HUB + "?keyFilter=" + reservationId;
            RestTemplate restTemplate = new RestTemplate();

            // Create HttpEntity
            HttpEntity<Void> entity = new HttpEntity<>(null);

            // Send request
            ResponseEntity<ReservationResponseDTO> response = restTemplate.exchange(url, HttpMethod.DELETE, entity,
                ReservationResponseDTO.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                logger.info("DELETE MOBILITY-HUB Response body: {}", response.getBody());
                return ERROR_CODE_0;
            }

        } catch (Exception e) {
            logger.error("EXCEPTION IN CALL DELETE MOBILITY-HUB: {} ", e.getMessage());
            return ERROR_CODE_4;
        }
        return ERROR_CODE_4;
    }
}
