package com.virtusa.creditunionapplication.model;

public class FinancialEligibilityResponse {

    private String firstName;
    private String lastName;
    private String error;

    public FinancialEligibilityResponse(String firstName, String lastName, String error) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.error = error;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
