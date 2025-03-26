package com.next.logistic.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class Statuses {

    private String key;
    private String value;
    private String validFrom;
    private String validUntil;
}
