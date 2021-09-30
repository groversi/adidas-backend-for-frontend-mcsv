package com.adidas.bff.services;

import com.adidas.bff.dto.request.SubscriptionRequest;
import com.adidas.bff.dto.response.SubscriptionResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GatewayService {

    SubscriptionResponse createSubscription(SubscriptionRequest subscriptionRequest);

    SubscriptionResponse getSubscription(String subscriptionId);

    List<SubscriptionResponse> getSubscriptions();

    void cancelSubscription(String subscriptionId);
}
