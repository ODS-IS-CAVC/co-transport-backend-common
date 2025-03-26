package com.next.logistic.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrspIsr {

    @JsonProperty("trsp_instruction_id")
    private String trspInstructionId;
}
