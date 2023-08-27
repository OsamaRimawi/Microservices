package com.example.ordermicroservice.DTO;

import java.util.List;

public class OrderDTO {


    private String orderNumber;
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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderLineItemsDTO=" + orderLineItemsDTO +
                '}';
    }
}
