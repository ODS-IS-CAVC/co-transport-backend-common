package com.next.logistic.data.util;

import com.next.logistic.authorization.UserContext;
import com.next.logistic.data.config.CommonDataConfigProperties;
import com.next.logistic.data.dto.request.ConstantRequestDTO;
import com.next.logistic.data.dto.request.TestCaseConstant;
import com.next.logistic.data.dto.request.RequestInfoDTO;
import com.next.logistic.data.dto.request.SemiDynamicInfoRequestDTO;
import com.next.logistic.data.dto.response.SemiDynamicInfoResponseDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.format.DateTimeFormatter;

/**
 * バリデーションユーティリティクラス。
 *
 * @author Next Logistics
 */
@Service
public class ValidateUtil {

    private static final Logger logger = LoggerFactory.getLogger(ValidateUtil.class);

    private static final String API_URL = "/api/v1/semiDynamicInfo";
    private static final int ERROR_CODE_0 = 1000; // Pass validate
    private static final int ERROR_CODE_1 = 1001; // Delay 30 minutes
    private static final int ERROR_CODE_2 = 1002; // Stop vehicle due to rainfall 3h
    private static final int ERROR_CODE_3 = 1003; // Stop vehicle due to wind speed
    private static final int ERROR_CODE_4 = 1004; // delay traffic recovered afternoon
    private static final int ERROR_CODE_5 = 1005; // delay traffic allday
    private static final int ERROR_CODE_25 = 1025; // Start
    private static final int ERROR_CODE_26 = 1026; // End

    private static final String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";

    @Autowired
    private CommonDataConfigProperties commonDataConfigProperties;
    @Resource(name = "userContext")
    UserContext userContext;
    /**
     * バリデーションを実行する。
     *
     * @param day           日付
     * @param departureTime 出発時間
     * @param arrivalTime   到着時間
     * @return エラーコード
     */
    public int validateCommon(LocalDate day, LocalTime departureTime, LocalTime arrivalTime) {
        try {
            // Check test case
            if (commonDataConfigProperties.isMockData() || userContext.getUser().isDebug()) {
                return TestCaseConstant.simulateErrorCode(day, departureTime, arrivalTime);
            }
            // End check test case
            List<LocalDateTime> targetTimes = calculateTargetTimes(day, departureTime, arrivalTime);
            return validateTargetTimes(targetTimes);
        } catch (Exception e) {
//            logger.error("Exception occurred during validation: ", e);
            return ERROR_CODE_0;
        }
    }


    /**
     * APIを呼び出してバリデーションを実行する。
     *
     * @param requestConstant リクエスト定数
     * @return エラーコード
     */
    private int callApiAndValidate(Map<String, Object> requestConstant, LocalDateTime targetTime) {
        int errorCode = ERROR_CODE_0;
        try {
            String url = commonDataConfigProperties.getDomainGateway() + API_URL;
            RestTemplate restTemplate = new RestTemplate();

            SemiDynamicInfoRequestDTO requestDTO = new SemiDynamicInfoRequestDTO();
            requestDTO.setTargetTime(targetTime);
            requestDTO.setRequestInfo((List<RequestInfoDTO>) requestConstant.get(ConstantRequestDTO.REQUEST_INFO));
            // Create HttpEntity
            HttpEntity<SemiDynamicInfoRequestDTO> entity = new HttpEntity<>(requestDTO);

            // Send request
            ResponseEntity<SemiDynamicInfoResponseDTO> response = restTemplate.exchange(url, HttpMethod.POST, entity,
                SemiDynamicInfoResponseDTO.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                SemiDynamicInfoResponseDTO jsonResponse = response.getBody();

                if (jsonResponse != null) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                    LocalDateTime checkDateTime = targetTime.toLocalDate().atTime(LocalTime.of(12, 0, 0));
                    // Check weather info
                    for (SemiDynamicInfoResponseDTO.Level1ResponseDTO level1 : jsonResponse.getAttribute()
                        .getLevel1()) {
                        double rainfall = level1.getWeatherInfo().getRainfall();
                        double windSpeed = level1.getWeatherInfo().getWindSpeed();

                        if (rainfall >= 50) {
                            // Set trsp_is_vehicle_stop_succeeded = true
                            return ERROR_CODE_2;
                        }
                        if (windSpeed >= 30) {
                            // Set trsp_is_vehicle_stop_succeeded = true
                            return ERROR_CODE_3;
                        }
                        if (rainfall < 20 && windSpeed < 10) {
                            // No issues
                        }
                        if (rainfall >= 20 || windSpeed >= 10) {
                            // trsp_incidents = null
                            errorCode = ERROR_CODE_1;
                        }
                        // Check traffic info
                        if (level1.getLevel2() != null) {
                            for (SemiDynamicInfoResponseDTO.Level2ResponseDTO level2 : level1.getLevel2()) {
                                for (SemiDynamicInfoResponseDTO.EventRegulationInfo eventRegulationInfo : level2.getEventRegulationInfo()) {
                                    String plannedEndTime = eventRegulationInfo.getPlanedEndTime();
                                    if (plannedEndTime != null) {
                                        LocalDateTime plannedEndTimeTime = LocalDateTime.parse(plannedEndTime, formatter);
                                        if (plannedEndTimeTime.isBefore(checkDateTime) || plannedEndTimeTime.equals(checkDateTime)) {
                                            return ERROR_CODE_4;
                                        } else if (plannedEndTimeTime.isAfter(checkDateTime)) {
                                            return ERROR_CODE_5;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                return ERROR_CODE_0;
            }
        } catch (Exception e) {
            // TODO: can not checking validate semiDynamicInfo
            return ERROR_CODE_0;
        }

        return errorCode;
    }

    /**
     * リクエスト定数を取得する。
     *
     * @param requestNumber リクエスト番号
     * @return リクエスト定数
     */
    private Map<String, Object> getRequestConstant(int requestNumber) {
        Map<String, Object> requestConstant;
        switch (requestNumber) {
            case 1:
                requestConstant = ConstantRequestDTO.REQUEST_1;
                break;
            case 2:
                requestConstant = ConstantRequestDTO.REQUEST_2;
                break;
            case 3:
                requestConstant = ConstantRequestDTO.REQUEST_3;
                break;
            case 4:
                requestConstant = ConstantRequestDTO.REQUEST_4;
                break;
            case 5:
                requestConstant = ConstantRequestDTO.REQUEST_5;
                break;
            case 6:
                requestConstant = ConstantRequestDTO.REQUEST_6;
                break;
            case 7:
                requestConstant = ConstantRequestDTO.REQUEST_7;
                break;
            case 8:
                requestConstant = ConstantRequestDTO.REQUEST_8;
                break;
            case 9:
                requestConstant = ConstantRequestDTO.REQUEST_9;
                break;
            case 10:
                requestConstant = ConstantRequestDTO.REQUEST_10;
                break;
            case 11:
                requestConstant = ConstantRequestDTO.REQUEST_11;
                break;
            case 12:
                requestConstant = ConstantRequestDTO.REQUEST_12;
                break;
            default:
                throw new IllegalArgumentException("Invalid request number");
        }
        return requestConstant;
    }

    /**
     * ターゲット時間を計算する。
     *
     * @param day           日付
     * @param departureTime 出発時間
     * @param arrivalTime   到着時間
     * @return ターゲット時間
     */
    private List<LocalDateTime> calculateTargetTimes(LocalDate day, LocalTime departureTime, LocalTime arrivalTime) {
        List<LocalDateTime> targetTimes = new ArrayList<>();
        LocalDateTime startDateTime = LocalDateTime.of(day, departureTime);
        
        LocalDateTime endDateTime;
        if (arrivalTime.isBefore(departureTime)) {
            endDateTime = LocalDateTime.of(day.plusDays(1), arrivalTime);
        } else {
            endDateTime = LocalDateTime.of(day, arrivalTime);
        }
        
        LocalDateTime currentTime = startDateTime.truncatedTo(ChronoUnit.HOURS);

        while (!currentTime.isAfter(endDateTime)) {
            targetTimes.add(currentTime);
            currentTime = currentTime.plusHours(1);
        }

        return targetTimes;
    }

    /**
     * ターゲット時間をバリデーションする。
     *
     * @param targetTimes ターゲット時間
     * @return エラーコード
     */
    private int validateTargetTimes(List<LocalDateTime> targetTimes) {
        int errorCode = ERROR_CODE_0;
        for (LocalDateTime targetTime : targetTimes) {
            for (int requestNumber = 1; requestNumber <= 12; requestNumber++) {
                int errorCodeTmp = callApiAndValidate(getRequestConstant(requestNumber), targetTime);
                if (errorCodeTmp == ERROR_CODE_1) {
                    errorCode = ERROR_CODE_1;
                } else if (errorCodeTmp != ERROR_CODE_0) {
                    return errorCodeTmp;
                }
            }
        }
        return errorCode;
    }

    /**
     * エラーメッセージを取得する。
     *
     * @param errorCode エラーコード
     * @return エラーメッセージ
     */
    public String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case ERROR_CODE_0:
                return "状態 : 正常走行可能";
            case ERROR_CODE_1:
                return "状態 : 遅延発生<br>状況 : 気象情報（雨量：20mm/h、風速：10m/s）<br>時間 : 30分遅延";
            case ERROR_CODE_2:
                return "状態 : 停止<br>状況 : 気象情報（雨量：50mm/h）<br>時間 : 1時に解除予定";
            case ERROR_CODE_3:
                return "状態 : 停止<br>状況 : 気象情報（風速：30m/s）<br>時間 : 1時に解除予定";
            case ERROR_CODE_4:
                return "状態 : 停止<br>状況 : 交通情報（渋滞発生）<br>時間 : ４時に解除予定";
            case ERROR_CODE_5:
                return "状態 : 停止<br>状況 : 交通情報（渋滞発生）<br>時間 : 当日復旧予定なし";
            case ERROR_CODE_25:
                return "駿河湾沼津 出発";
            case ERROR_CODE_26:
                return "浜松 到着";
            default:
                return "不明なエラー";
        }
    }

    /**
     * エラーメッセージを取得する。
     *
     * @param category カテゴリ
     * @param incident インシデント
     * @return エラーメッセージ
     */
    public String getErrorMessage(String category, String incident) {
        if (category == null || incident == null) {
            return "";
        }

        switch (category) {
            case "VEHICLE_CONTROL_INCIDENT":
                switch (incident) {
                    case "ADK_ERROR":
                        return "ADシステム異常";
                    case "ECU_ERROR":
                        return "エンジン制御系異常";
                    case "DPD_ERROR":
                        return "後処理系異常";
                    case "TRANSMISSION_ERROR":
                        return "トランスミッション系異常";
                    case "STEERING_ERROR":
                        return "ステアリング系異常";
                    case "EBS_ERROR":
                        return "EBS系異常";
                    case "AIR_SUSPENSION_ERROR":
                        return "エアサス系異常";
                }
                break;

            case "OPERATIONAL_BASIC_INCIDENT":
                switch (incident) {
                    case "TIRE_PUNCTURE":
                        return "タイヤパンク";
                    case "VEHICLE_FIRE":
                        return "車両火災";
                    case "OIL_LEAKAGE":
                        return "オイル漏れ";
                    case "VOLTAGE_ERROR":
                        return "バッテリー上がり";
                    case "FUEL_SYSTEM_ERROR":
                        return "燃料系異常";
                    case "COOLING_SYSTEM_ERROR":
                        return "冷却系異常";
                    case "CARGO_COLLAPSE":
                        return "荷崩れ（荷崩れ発生）";
                    case "AIRBAG_EXPLOSION":
                        return "エアバッグの誤開情報";
                }
                break;

            case "WEATHER_INCIDENT":
                switch (incident) {
                    case "SNOW_DEPTH_EXCEEDED":
                        return "積雪深さ OOcm 以上";
                    case "WIND_SPEED_EXCEEDED":
                        return "最大風速 10m/秒 以上";
                    case "RAINFALL_EXCEEDED":
                        return "降雨量 20mm/時 以上";
                    case "SNOWFALL_EXCEEDED":
                        return "降雪量 OOcm/時 以上";
                    case "THICK_FOG":
                        return "濃霧";
                    case "FROZEN_ROAD_SURFACE":
                        return "路面凍結";
                }
                break;

            case "TRAFFIC_INCIDENT":
                switch (incident) {
                    case "OTHER_VEHICLE_ACCIDENT":
                        return "事故（他車両）";
                    case "OTHER_VEHICLE_STOPPED":
                        return "他停止車両";
                    case "TRAFFIC_CONGESTION":
                        return "渋滞";
                    case "VEHICLE_DRIVING_WRONG_WAY":
                        return "逆走車";
                }
                break;
        }

        return null;
    }

    /**
     * メッセージ情報を取得する。
     *
     * @param code         コード
     * @param locationName 場所名
     * @return メッセージ情報
     */
    public String getMessageInfo(int code, String locationName) {
        switch (code) {
            case ERROR_CODE_25:
                return locationName + " 出発";
            case ERROR_CODE_26:
                return locationName + " 到着";
            default:
                return null;
        }
    }
}
