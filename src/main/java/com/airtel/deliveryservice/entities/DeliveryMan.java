package com.airtel.deliveryservice.entities;

import com.airtel.deliveryservice.model.DeliveryManStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * order table entity
 *
 * @author dmalhotra
 */
@Getter
@Setter
public class DeliveryMan extends Deletable {

    @Column(name = "name", columnDefinition = "varchar(255)", nullable = false)
    private String name;

    @Column(name = "order_assigned", columnDefinition = "bigint", nullable = false)
    private Long orderId;

    @Column(name = "status", columnDefinition = "varchar(255)", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryManStatus status;

    @Column(name = "active", columnDefinition = "tinyint(1)", nullable = false)
    private Long active;
}
