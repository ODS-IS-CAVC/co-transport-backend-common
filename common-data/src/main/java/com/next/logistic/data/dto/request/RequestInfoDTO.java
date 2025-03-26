package com.next.logistic.data.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * リクエスト情報DTOクラス。
 *
 * @author Next Logistics
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RequestInfoDTO {

    private Integer targetData;

    private Integer requestFormat;

    private RequestAreaDTO requestArea;
}
