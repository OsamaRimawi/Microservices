package com.example.ordermicroservice.DTO;

import java.util.List;

public class OrderDTO {


    private String orderNumber;
    private String email;
    private String phoneNumber;
    private List<OrderLineItemsDTO> orderLineItemsDTO;

    public OrderDTO() {
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }


    public List<OrderLineItemsDTO> getOrderLineItemsDTO() {
        return orderLineItemsDTO;
    }

    public void setOrderLineItemsDTO(List<OrderLineItemsDTO> orderLineItemsDTO) {
        this.orderLineItemsDTO = orderLineItemsDTO;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderLineItemsDTO=" + orderLineItemsDTO +
                '}';
    }
}
