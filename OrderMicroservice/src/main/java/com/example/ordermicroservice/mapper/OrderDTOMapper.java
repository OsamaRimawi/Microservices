package com.example.ordermicroservice.mapper;


import com.example.ordermicroservice.DTO.OrderDTO;
import com.example.ordermicroservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderDTOMapper {

    OrderDTOMapper INSTANCE = Mappers.getMapper(OrderDTOMapper.class);

    OrderDTO orderToOrderDTO(Order order);

    Order orderDTOToOrder(OrderDTO orderDTO);

    void updateOrderFromDTO(OrderDTO orderDTO, @MappingTarget Order order);


}
