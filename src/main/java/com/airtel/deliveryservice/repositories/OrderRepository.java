package com.airtel.deliveryservice.repositories;

import com.airtel.deliveryservice.entities.Order;
import com.airtel.deliveryservice.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring JPA repository for {@link Order} entity
 *
 * @author dmalhotra
 * @version 1.0.0
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByDeliveryManAndStatus(Long deliveryMan, OrderStatus status);
}
