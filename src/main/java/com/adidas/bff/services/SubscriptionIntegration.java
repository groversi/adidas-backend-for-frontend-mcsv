package com.adidas.bff.services;

import com.adidas.bff.dto.request.SubscriptionRequest;
import com.adidas.bff.dto.response.SubscriptionResponse;
import com.adidas.bff.exceptions.customExceptions.BadRequestException;
import com.adidas.bff.exceptions.customExceptions.MethodForbidenException;
import com.adidas.bff.exceptions.customExceptions.MethodNotAllowedException;
import com.adidas.bff.exceptions.customExceptions.ResourceNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(name = "Subscription", url = "${feign.url.subscriptions}")
public interface SubscriptionIntegration {

    @RequestMapping(value = "/v1/subscription",
            produces = {"application/json"}, method = RequestMethod.POST)
    SubscriptionResponse createSubscription(@RequestBody SubscriptionRequest subscriptionRequest);

    @RequestMapping(value = "/v1/subscription/{subscriptionId}",
            produces = {"application/json"}, method = RequestMethod.GET)
    SubscriptionResponse getSubscription(@PathVariable String subscriptionId);

    @RequestMapping(value = "/v1/subscription",
            produces = {"application/json"}, method = RequestMethod.GET)
    List<SubscriptionResponse> getSubscriptions();

    @RequestMapping(value = "/v1/subscription/{subscriptionId}",
            produces = {"application/json"}, method = RequestMethod.DELETE)
    void cancelSubscription(@PathVariable String subscriptionId);

}
