package com.adidas.bff.controllers;

import com.adidas.bff.dto.request.SubscriptionRequest;
import com.adidas.bff.dto.response.SubscriptionResponse;
import com.adidas.bff.services.GatewayService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class BffController {

    private static final Logger log = LoggerFactory.getLogger(BffController.class);

    @Autowired
    private GatewayService gatewayService;

    @GetMapping(value = "/subscription/{subscriptionId}")
    @ApiOperation(value = "Search a subscription by id")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true)
    public ResponseEntity<SubscriptionResponse> getSubscriptionById(
            @Valid @PathVariable
            @NotEmpty(message = "Id is required")
                    String subscriptionId
    ) {

        log.info("Search subscription by id request received");
        SubscriptionResponse subscription = gatewayService.getSubscription(subscriptionId);
        if(subscription == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @GetMapping(value = "/subscription")
    @ApiOperation(value = "List all subscriptions")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true)
    public ResponseEntity<List<SubscriptionResponse>> getAllSubscriptions() {

        log.info("Get all subscriptions request received");
        List<SubscriptionResponse> subscriptions = gatewayService.getSubscriptions();
        if(subscriptions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @PostMapping(value = "/subscription")
    @ApiOperation(value = "Create a subscription")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true)
    public ResponseEntity<SubscriptionResponse> createSubscription(
            @RequestBody SubscriptionRequest subscriptionDTO) {

        log.info("Create a subscription request received");
        return new ResponseEntity<>(gatewayService.createSubscription(subscriptionDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/subscription/{subscriptionId}")
    @ApiOperation(value = "Cancel a subscription")
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true)
    public ResponseEntity<?> deleteSubscription(
            @Valid @PathVariable
            @NotEmpty(message = "Id is required")
                    String subscriptionId) {

        log.info("Delete subscription request received");
        gatewayService.cancelSubscription(subscriptionId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
