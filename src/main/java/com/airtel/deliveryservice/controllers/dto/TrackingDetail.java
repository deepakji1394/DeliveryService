package com.airtel.deliveryservice.controllers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingDetail {

    private String status;

    @JsonProperty("delivery_time_left")
    private Integer timeLeftForDelivery;
}
