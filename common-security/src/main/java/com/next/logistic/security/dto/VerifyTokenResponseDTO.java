package com.next.logistic.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VerifyTokenResponseDTO {
    @JsonProperty("active")
    private Boolean active;
}
