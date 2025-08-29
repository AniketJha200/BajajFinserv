package com.aniket.bajaj_hiring_task.dto;

public class WebhookResponse {

    private String webhookURL;
    private String accessToken;

    // No-argument constructor is needed for JSON deserialization frameworks like Jackson
    public WebhookResponse() {
    }

    // All-arguments constructor for easier object creation
    public WebhookResponse(String webhookURL, String accessToken) {
        this.webhookURL = webhookURL;
        this.accessToken = accessToken;
    }

    // Getters and Setters are used by Jackson to set the private fields from the JSON response
    public String getWebhookURL() {
        return webhookURL;
    }

    public void setWebhookURL(String webhookURL) {
        this.webhookURL = webhookURL;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    // toString() method is helpful for logging and debugging
    @Override
    public String toString() {
        return "WebhookResponse{" +
                "webhookURL='" + webhookURL + '\'' +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
