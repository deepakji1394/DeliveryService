package com.airtel.deliveryservice.repositories;

import com.airtel.deliveryservice.entities.DeliveryMan;
import com.airtel.deliveryservice.model.DeliveryManStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring JPA repository for {@link DeliveryMan} entity
 *
 * @author dmalhotra
 * @version 1.0.0
 */
@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryMan, Long> {

    List<DeliveryMan> findAllByStatus(DeliveryManStatus status);

    List<DeliveryMan> findAllByActive(Integer active);
}
