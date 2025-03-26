package com.next.logistic.data.dto.response;

import com.next.logistic.data.dto.Attribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class ReservationResponseDTO {

    private String dataModelType;
    private Attribute attribute;
}
