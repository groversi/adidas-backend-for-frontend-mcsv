package com.adidas.bff.dto.response;

import com.adidas.bff.util.GenderEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscription;

import java.time.LocalDate;



public class SubscriptionResponse {

    private String subscriptionId;
    private Boolean enabled;
    private String email;
    private String firstName;
    private GenderEnum gender;
    private LocalDate dateOfBirth;
    private Boolean subscriptionConsent;
    private Integer campaignId;


    public SubscriptionResponse(){}

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getSubscriptionConsent() {
        return subscriptionConsent;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSubscriptionConsent(Boolean subscriptionConsent) {
        this.subscriptionConsent = subscriptionConsent;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }
}
