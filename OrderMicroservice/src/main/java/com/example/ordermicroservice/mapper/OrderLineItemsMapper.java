package com.example.ordermicroservice.mapper;

import com.example.ordermicroservice.DTO.OrderLineItemsDTO;
import com.example.ordermicroservice.model.OrderLineItems;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface OrderLineItemsMapper {


    OrderLineItemsMapper INSTANCE = Mappers.getMapper(OrderLineItemsMapper.class);

    OrderLineItemsDTO orderLineItemsToOrderLineItemsDTO(OrderLineItems orderLineItems);


    OrderLineItems orderLineItemsDTOToOrderLineItems(OrderLineItemsDTO orderLineItemsDTO);

}
