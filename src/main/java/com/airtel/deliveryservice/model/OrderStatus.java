package com.airtel.deliveryservice.model;

public enum OrderStatus {
        PLACED,
        IN_PROGRESS,
        OUT_FOR_DELIVERY,
        DELIVERED;

    private OrderStatus() {
    }
}
