package com.adidas.bff.dto.request;

public class SubscriptionRequest {

    private String email;
    private String firstName;
    private String gender;
    private String dateOfBirth;
    private Boolean subscriptionConsent;
    private Integer campaignId;

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Boolean getSubscriptionConsent() {
        return subscriptionConsent;
    }

    public Integer getCampaignId() {
        return campaignId;
    }
}
