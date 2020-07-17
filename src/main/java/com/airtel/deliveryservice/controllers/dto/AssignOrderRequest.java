package com.airtel.deliveryservice.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignOrderRequest {

    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("delivery_man_id")
    private Long deliveryManId;
}
