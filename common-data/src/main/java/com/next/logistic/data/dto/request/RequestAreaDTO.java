package com.next.logistic.data.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * リクエストエリアDTOクラス。
 *
 * @author Next Logistics
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class RequestAreaDTO {

    private String spatialID;
    private Double[][] bbox;
    private String areaID;
}
