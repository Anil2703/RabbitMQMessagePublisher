package com.rocky.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;


import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class RabbitMQMessage {

    @NotEmpty(message = "customerID cannot be Empty or Null")
    @JsonProperty("customerID")
    private String customerID;

    @NotEmpty(message = "customerName cannot be Empty or Null")
    @JsonProperty("customerName")
    private String customerName;

    @NotEmpty(message = "customerEvent cannot be Empty or Null")
    @JsonProperty("customerEvent")
    private String customerEvent;

    @NotEmpty(message = "customerType cannot be Empty or Null")
    @JsonProperty("customerType")
    private String customerType;

    @NotEmpty(message = "customerContact cannot be Empty or Null")
    @JsonProperty("customerContact")
    private String customerContact;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEvent() {
        return customerEvent;
    }

    public void setCustomerEvent(String customerEvent) {
        this.customerEvent = customerEvent;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }
}
