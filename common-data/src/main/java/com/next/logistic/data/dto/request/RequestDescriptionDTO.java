package com.next.logistic.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDescriptionDTO {

    private String mobilityHubId;
    private String freightId;
    private String truckId;
    private String sizeClass;
}
