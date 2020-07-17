package com.airtel.deliveryservice.service;

import com.airtel.deliveryservice.controllers.dto.ActiveDeliverManResponse;
import com.airtel.deliveryservice.controllers.dto.AssignOrderRequest;
import com.airtel.deliveryservice.controllers.dto.AssignOrderResponse;
import com.airtel.deliveryservice.controllers.dto.TrackingDetail;
import com.airtel.deliveryservice.handler.model.BadRequestException;
import com.airtel.deliveryservice.handler.model.NotFoundException;
import com.airtel.deliveryservice.handler.model.ServiceException;

import java.util.List;

public interface DeliveryService {
    List<ActiveDeliverManResponse> getActiveDeliveryMen() throws NotFoundException, BadRequestException, ServiceException;

    AssignOrderResponse assignOrder(AssignOrderRequest request) throws NotFoundException, BadRequestException, ServiceException;

    TrackingDetail trackDeliveryMan(Long deliveryManId) throws NotFoundException, BadRequestException, ServiceException;
}
