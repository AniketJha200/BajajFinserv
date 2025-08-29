package com.katoch.bajaj_hiring_task.dto;

public class SolutionRequest {

    private String finalQuery;

    // A no-argument constructor is necessary for JSON deserialization frameworks.
    public SolutionRequest() {
    }

    // A constructor with arguments for easily creating an instance of this class.
    public SolutionRequest(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    // Getters and setters are used by the Jackson library to create the JSON body from this object.
    public String getFinalQuery() {
        return finalQuery;
    }

    public void setFinalQuery(String finalQuery) {
        this.finalQuery = finalQuery;
    }

    // The toString() method is useful for logging and debugging.
    @Override
    public String toString() {
        return "SolutionRequest{" +
                "finalQuery='" + finalQuery + '\'' +
                '}';
    }
}