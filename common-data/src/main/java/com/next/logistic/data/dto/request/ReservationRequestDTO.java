package com.next.logistic.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDTO {

    private String dataModelType;
    private AttributeRequestDTO attribute;
}
