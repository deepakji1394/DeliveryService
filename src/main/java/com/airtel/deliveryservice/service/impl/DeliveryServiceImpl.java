package com.airtel.deliveryservice.service.impl;

import com.airtel.deliveryservice.controllers.dto.ActiveDeliverManResponse;
import com.airtel.deliveryservice.controllers.dto.AssignOrderRequest;
import com.airtel.deliveryservice.controllers.dto.AssignOrderResponse;
import com.airtel.deliveryservice.controllers.dto.TrackingDetail;
import com.airtel.deliveryservice.entities.DeliveryMan;
import com.airtel.deliveryservice.entities.Order;
import com.airtel.deliveryservice.handler.model.BadRequestException;
import com.airtel.deliveryservice.handler.model.ErrorCode;
import com.airtel.deliveryservice.handler.model.NotFoundException;
import com.airtel.deliveryservice.handler.model.ServiceException;
import com.airtel.deliveryservice.model.DeliveryManStatus;
import com.airtel.deliveryservice.model.OrderStatus;
import com.airtel.deliveryservice.repositories.DeliveryManRepository;
import com.airtel.deliveryservice.repositories.OrderRepository;
import com.airtel.deliveryservice.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    /**
     * private static class level logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryServiceImpl.class);

    @Autowired
    private DeliveryManRepository deliveryManRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<ActiveDeliverManResponse> getActiveDeliveryMen() throws NotFoundException, BadRequestException, ServiceException {
        List<DeliveryMan> deliveryMens = deliveryManRepository.findAllByActive(1);
        if (CollectionUtils.isEmpty(deliveryMens)) {
            return Collections.EMPTY_LIST;
        }
        return deliveryMens.stream().map(e -> new ActiveDeliverManResponse(e.getId(), e.getStatus().toString())).collect(Collectors.toList());
    }

    @Override
    public AssignOrderResponse assignOrder(AssignOrderRequest request) throws NotFoundException, BadRequestException, ServiceException {
        Optional<Order> orderOptional = orderRepository.findById(request.getOrderId());
        if (!orderOptional.isPresent()) {
            throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "order not found");
        }
        Order order = orderOptional.get();

        Optional<DeliveryMan> deliveryManOptional = deliveryManRepository.findById(request.getDeliveryManId());
        if (!deliveryManOptional.isPresent()) {
            throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "delivery guy not found");
        }
        DeliveryMan deliveryMan = deliveryManOptional.get();
        if (deliveryMan.getStatus() == DeliveryManStatus.ON_DELIVERY) {
            throw new BadRequestException(ErrorCode.ALREADY_ON_DELIVERY, "delivery man already on delivery");
        }
        order.setDeliveryMan(deliveryMan.getId());
        order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
        order.setDeliveryStartTime(Instant.now().toEpochMilli());
        return new AssignOrderResponse("ACCEPTED");
    }

    @Override
    public TrackingDetail trackDeliveryMan(Long deliveryManId) throws NotFoundException, BadRequestException, ServiceException {

        Optional<DeliveryMan> deliveryManOptional = deliveryManRepository.findById(deliveryManId);
        if (!deliveryManOptional.isPresent()) {
            throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "delivery guy not found");
        }
        DeliveryMan deliveryMan = deliveryManOptional.get();
        TrackingDetail trackingDetail = new TrackingDetail();
        if (deliveryMan.getStatus() == DeliveryManStatus.AVAILABLE) {
            trackingDetail.setStatus(DeliveryManStatus.AVAILABLE.toString());
            return trackingDetail;
        }
        Optional<Order> orderOptional = orderRepository.findByDeliveryManAndStatus(deliveryManId, OrderStatus.OUT_FOR_DELIVERY);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "order not found");
        }
        Order order = orderOptional.get();

        trackingDetail.setStatus(DeliveryManStatus.ON_DELIVERY.toString());
        Integer timeLeft = order.getDeliveryTime() - Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(Instant.now().toEpochMilli() - order.getDeliveryStartTime())).intValue();
        trackingDetail.setTimeLeftForDelivery(timeLeft);
        return trackingDetail;
    }
}
