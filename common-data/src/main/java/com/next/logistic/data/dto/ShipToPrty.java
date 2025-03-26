package com.next.logistic.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShipToPrty {

    @JsonProperty("ship_to_prty_brnc_off_id")
    private String shipToPrtyBrncOffId;
}
