package com.next.logistic.data.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.next.logistic.data.dto.LogsSrvcPrv;
import com.next.logistic.data.dto.ShipFromPrty;
import com.next.logistic.data.dto.ShipToPrty;
import com.next.logistic.data.dto.TrspIsr;
import com.next.logistic.data.dto.TrspSrvc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = false)
public class TrackBySipRequestDTO {

    @JsonProperty("is_post")
    private boolean isPost = true;

    @JsonProperty("trsp_isr")
    private TrspIsr trspIsr;

    @JsonProperty("trsp_srvc")
    private TrspSrvc trspSrvc;

    @JsonProperty("logs_srvc_prv")
    private LogsSrvcPrv logsSrvcPrv;

    @JsonProperty("ship_from_prty")
    private ShipFromPrty shipFromPrty;

    @JsonProperty("ship_to_prty")
    private ShipToPrty shipToPrty;

}
