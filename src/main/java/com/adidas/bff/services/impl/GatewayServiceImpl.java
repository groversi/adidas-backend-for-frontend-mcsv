package com.adidas.bff.services.impl;

import com.adidas.bff.controllers.BffController;
import com.adidas.bff.dto.request.SubscriptionRequest;
import com.adidas.bff.dto.response.SubscriptionResponse;
import com.adidas.bff.exceptions.customExceptions.*;
import com.adidas.bff.services.GatewayService;
import com.adidas.bff.services.SubscriptionIntegration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GatewayServiceImpl implements GatewayService {

    private static final Logger log = LoggerFactory.getLogger(BffController.class);

    @Autowired
    private SubscriptionIntegration subscriptionIntegration;

    @Override
    @HystrixCommand(fallbackMethod = "defaultAnswerForCreateSubscription", ignoreExceptions = { BadRequestException.class, MethodForbidenException.class, MethodNotAllowedException.class, ResourceNotFoundException.class})
    public SubscriptionResponse createSubscription(SubscriptionRequest subscriptionRequest) {
        return subscriptionIntegration.createSubscription(subscriptionRequest);
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultAnswerForListSubscription", ignoreExceptions = { BadRequestException.class, MethodForbidenException.class, MethodNotAllowedException.class, ResourceNotFoundException.class})
    public SubscriptionResponse getSubscription(String subscriptionId) {
        return subscriptionIntegration.getSubscription(subscriptionId);
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultAnswerForListSubscriptions", ignoreExceptions = { BadRequestException.class, MethodForbidenException.class, MethodNotAllowedException.class, ResourceNotFoundException.class})
    public List<SubscriptionResponse> getSubscriptions() {
        return subscriptionIntegration.getSubscriptions();
    }

    @Override
    @HystrixCommand(fallbackMethod = "defaultAnswerForCancelSubscription", ignoreExceptions = { BadRequestException.class, MethodForbidenException.class, MethodNotAllowedException.class, ResourceNotFoundException.class})
    public void cancelSubscription(String subscriptionId) {
        subscriptionIntegration.cancelSubscription(subscriptionId);
    }

    private SubscriptionResponse defaultAnswerForCreateSubscription(SubscriptionRequest subscriptionRequest) {
        log.info("Returning default answer because circuit is open");
        throw new OpenCircuitBreakException();
    }

    private SubscriptionResponse defaultAnswerForListSubscription(String subscriptionId) {
        log.info("Returning default answer because circuit is open");
        throw new OpenCircuitBreakException();
    }

    private List<SubscriptionResponse> defaultAnswerForListSubscriptions() {
        log.info("Returning default answer because circuit is open");
        throw new OpenCircuitBreakException();
    }

    private void defaultAnswerForCancelSubscription(String subscriptionId) {
        log.info("Returning default answer because circuit is open");
        throw new OpenCircuitBreakException();
    }
}
