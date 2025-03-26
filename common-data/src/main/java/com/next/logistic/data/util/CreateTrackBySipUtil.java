package com.next.logistic.data.util;

import com.next.logistic.data.config.CommonDataConfigProperties;
import com.next.logistic.data.dto.LogsSrvcPrv;
import com.next.logistic.data.dto.ShipFromPrty;
import com.next.logistic.data.dto.ShipToPrty;
import com.next.logistic.data.dto.TrspIsr;
import com.next.logistic.data.dto.TrspSrvc;
import com.next.logistic.data.dto.request.TrackBySipRequestDTO;
import com.next.logistic.data.dto.response.RestTemplateApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * トラックバイSIPを作成するユーティリティクラス。
 *
 * @author Next Logistics
 */
@Service
public class CreateTrackBySipUtil {

    private final String API_TRACK_BY_SIP = "/api/v1/track/sip";

    @Autowired
    private CommonDataConfigProperties commonDataConfigProperties;

    /**
     * トラックバイSIPを作成するメソッド。
     *
     */
    public void createTrackBySip(Boolean isPost) {
        String url = commonDataConfigProperties.getDomainGateway() + API_TRACK_BY_SIP;
        RestTemplate restTemplate = new RestTemplate();

        TrackBySipRequestDTO request = new TrackBySipRequestDTO();
        if (isPost) {
            request.setPost(true);
        } else {
            request.setPost(false);
        }
        TrspIsr trspIsr = new TrspIsr();
        trspIsr.setTrspInstructionId("pla34");
        request.setTrspIsr(trspIsr);

        LogsSrvcPrv logsSrvcPrv = new LogsSrvcPrv();
        logsSrvcPrv.setLogsSrvcPrvPrtyNameTxt("324");
        request.setLogsSrvcPrv(logsSrvcPrv);

        ShipFromPrty shipFromPrty = new ShipFromPrty();
        shipFromPrty.setShipFromPrtyBrncOffId("324");
        request.setShipFromPrty(shipFromPrty);

        ShipToPrty shipToPrty = new ShipToPrty();
        shipToPrty.setShipToPrtyBrncOffId("324");
        request.setShipToPrty(shipToPrty);

        TrspSrvc trspSrvc = new TrspSrvc();
        trspSrvc.setCarLicensePltNumId("car123");
        trspSrvc.setAvbDateCllDate("20241214");
        trspSrvc.setAvbFromTimeOfCllTime("143000");
        trspSrvc.setApedArrToDate("20241214");
        trspSrvc.setApedArrToTimePrfmDttm("143000");
        request.setTrspSrvc(trspSrvc);

        // Create HttpEntity
        HttpEntity<TrackBySipRequestDTO> entity = new HttpEntity<>(request);

        // Send request
        ResponseEntity<RestTemplateApiResponse> response = restTemplate.exchange(url, HttpMethod.PUT, entity,
            RestTemplateApiResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            response.getBody();
        }
    }
}

