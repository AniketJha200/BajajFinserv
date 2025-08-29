package com.katoch.bajaj_hiring_task.dto;

public class WebhookRequest {

    private String name;
    private String regNo;
    private String email;

    // No-argument constructor (required for JSON deserialization)
    public WebhookRequest() {
    }

    // All-arguments constructor for convenience
    public WebhookRequest(String name, String regNo, String email) {
        this.name = name;
        this.regNo = regNo;
        this.email = email;
    }

    // Getters and Setters
    // (Required for the Jackson library to serialize this object into JSON)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "WebhookRequest{" +
                "name='" + name + '\'' +
                ", regNo='" + regNo + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}