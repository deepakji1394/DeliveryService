package com.airtel.deliveryservice.controllers;

import com.airtel.deliveryservice.controllers.dto.ActiveDeliverManResponse;
import com.airtel.deliveryservice.controllers.dto.AssignOrderRequest;
import com.airtel.deliveryservice.controllers.dto.AssignOrderResponse;
import com.airtel.deliveryservice.controllers.dto.TrackingDetail;
import com.airtel.deliveryservice.handler.model.BadRequestException;
import com.airtel.deliveryservice.handler.model.ErrorCode;
import com.airtel.deliveryservice.handler.model.NotFoundException;
import com.airtel.deliveryservice.handler.model.ServiceException;
import com.airtel.deliveryservice.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Rest controller for order service
 *
 * @author dmalhotra
 * @version 1.0.0
 */
@RestController
@RequestMapping("/")
public class DeliveryController {

    /**
     * private static class level logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);

    /**
     * order service obj
     */
    @Autowired
    private DeliveryService service;

    @GetMapping(value = "/api/v1/allActiveDeliveryMen", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getActiveDeliveryMen()  throws NotFoundException, BadRequestException, ServiceException {

        try {
            //TODO all basic validation before calling service and throw IllegalArgumentException if fails
        } catch (IllegalArgumentException ex) {
            // any illegal argument exception here means that client request is invalid and
            // must return 400
            throw new BadRequestException(ErrorCode.BAD_REQUEST, ex.getMessage(), ex);
        }
        List<ActiveDeliverManResponse> response = service.getActiveDeliveryMen();
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/api/v1/assignOrder", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> assignOrder(@RequestBody AssignOrderRequest request)  throws NotFoundException, BadRequestException, ServiceException {

        AssignOrderResponse response = null;
        try {
            //TODO all basic validation before calling service and throw IllegalArgumentException if fails
        } catch (IllegalArgumentException ex) {
            // any illegal argument exception here means that client request is invalid and
            // must return 400
            throw new BadRequestException(ErrorCode.BAD_REQUEST, ex.getMessage(), ex);
        }
        response = service.assignOrder(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/api/v1/trackDeliveryMan/{deliveryManId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> trackDeliveryMan(@PathVariable("deliveryManId") Long deliveryManId)  throws NotFoundException, BadRequestException, ServiceException {

        try {
            //TODO all basic validation before calling service and throw IllegalArgumentException if fails
        } catch (IllegalArgumentException ex) {
            // any illegal argument exception here means that client request is invalid and
            // must return 400
            throw new BadRequestException(ErrorCode.BAD_REQUEST, ex.getMessage(), ex);
        }
        TrackingDetail response = service.trackDeliveryMan(deliveryManId);
        return ResponseEntity.ok(response);
    }

}
