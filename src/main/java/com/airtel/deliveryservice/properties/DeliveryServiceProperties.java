package com.airtel.deliveryservice.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

/**
 * Base class to hold all the application specific properties
 *
 * @author dmalhotra
 * @version 1.0.0
 */
@Component
@ConfigurationProperties("deliveryService")
@Validated
@Getter
@Setter
public class DeliveryServiceProperties {

    /**
     * The app name
     */
    @NotEmpty
    private String appName;

    /**
     * The app version
     */
    @NotEmpty
    private String appVersion;

    /**
     * Generic error message for the server
     */
    @NotEmpty
    private String genericErrorMessage;
}
