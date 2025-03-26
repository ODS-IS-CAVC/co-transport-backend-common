package com.next.logistic.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusesRequestDTO {
    private RequestDescriptionDTO requestObject;
    private String value;
    private String validFrom;
    private String validUntil;
}
