package com.example.ordermicroservice.service;


import com.example.ordermicroservice.DTO.OrderDTO;
import com.example.ordermicroservice.DTO.OrderLineItemsDTO;
import com.example.ordermicroservice.mapper.OrderDTOMapper;
import com.example.ordermicroservice.mapper.OrderLineItemsMapper;
import com.example.ordermicroservice.model.Order;
import com.example.ordermicroservice.model.OrderLineItems;
import com.example.ordermicroservice.repository.OrderLineItemsRepository;
import com.example.ordermicroservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderLineItemsRepository orderLineItemsRepository;
    private final WebClient.Builder webClientBuilder;



    private final OrderDTOMapper orderDTOMapper;
    private final OrderLineItemsMapper orderLineItemsDTOMapper;


    @Autowired
    public OrderService(OrderRepository orderRepository, OrderLineItemsRepository orderLineItemsRepository, WebClient.Builder webClientBuilder, OrderDTOMapper orderDTOMapper, OrderLineItemsMapper orderLineItemsDTOMapper) {
        this.orderRepository = orderRepository;
        this.orderLineItemsRepository = orderLineItemsRepository;
        this.webClientBuilder = webClientBuilder;
        this.orderDTOMapper = orderDTOMapper;
        this.orderLineItemsDTOMapper = orderLineItemsDTOMapper;
    }

    public List<OrderDTO> getAll() {
        List<Order> orderList = orderRepository.findAll();

        List<OrderDTO> orderDTOList = orderList.stream()
                .map(order -> {
                    List<OrderLineItems> orderLineItemsList = order.getOrderLineItems();
                    List<OrderLineItemsDTO> orderLineItemsDTOList = orderLineItemsList.stream()
                            .map(orderLineItemsDTOMapper::orderLineItemsToOrderLineItemsDTO)
                            .collect(Collectors.toList());

                    OrderDTO orderDTO = orderDTOMapper.orderToOrderDTO(order);
                    orderDTO.setOrderLineItemsDTO(orderLineItemsDTOList);
                    return orderDTO;
                })
                .collect(Collectors.toList());

        return orderDTOList;
    }


    public OrderDTO getOrderByOrderNumber(String orderNumber) {

        if (orderNumber == null || orderNumber.isEmpty()) {
            throw new IllegalArgumentException("Invaild name and number parameters");
        }
        Order order = orderRepository.findByOrderNumber(orderNumber);
        if (order != null) {
            List<OrderLineItems> orderLineItemsList = order.getOrderLineItems();
            List<OrderLineItemsDTO> orderLineItemsDTOList = orderLineItemsList.stream()
                    .map(orderLineItemsDTOMapper::orderLineItemsToOrderLineItemsDTO)
                    .collect(Collectors.toList());

            OrderDTO orderDTO = orderDTOMapper.orderToOrderDTO(order);
            orderDTO.setOrderLineItemsDTO(orderLineItemsDTOList);
            return orderDTO;

        }
        return null;
    }


    public ResponseEntity<String> addOrder(OrderDTO orderDTO) {
        Order order = orderDTOMapper.orderDTOToOrder(orderDTO);
        List<OrderLineItemsDTO> orderLineItemsListDTO = orderDTO.getOrderLineItemsDTO();
        List<OrderLineItems> orderLineItemsList = orderLineItemsListDTO.stream()
                .map(orderLineItemsDTO -> {
                    OrderLineItems orderLineItems = orderLineItemsDTOMapper.orderLineItemsDTOToOrderLineItems(orderLineItemsDTO);
                    orderLineItems.setOrder(order);
                    boolean doesProductExist = webClientBuilder.build().get()
                            .uri("http://product-microservice/api/v1.0/product/check",
                                    uriBuilder -> uriBuilder.queryParam("name", orderLineItems.getProductName()).build())
                            .retrieve()
                            .bodyToMono(Boolean.class)
                            .block();

                    if (!doesProductExist) {
                        throw new IllegalArgumentException("Invaild Product name");
                    }
                    return orderLineItems;
                })
                .collect(Collectors.toList());
        order.setOrderLineItems(orderLineItemsList);
        orderRepository.save(order);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<String> updateOrder(OrderDTO orderDTO) {
        String orderNumber = orderDTO.getOrderNumber();
        Order existingOrder = orderRepository.findByOrderNumber(orderNumber);
        orderLineItemsRepository.deleteOrderLineItemsByOrderId(existingOrder.getId());
        existingOrder.getOrderLineItems().clear();
        List<OrderLineItemsDTO> orderLineItemsListDTO = orderDTO.getOrderLineItemsDTO();
        List<OrderLineItems> orderLineItemsList = orderLineItemsListDTO.stream()
                .map(orderLineItemsDTO -> {
                    OrderLineItems orderLineItems = orderLineItemsDTOMapper.orderLineItemsDTOToOrderLineItems(orderLineItemsDTO);
                    orderLineItems.setOrder(existingOrder);
                    boolean doesProductExist = webClientBuilder.build().get()
                            .uri("http://product-microservice/api/v1.0/product/check",
                                    uriBuilder -> uriBuilder.queryParam("name", orderLineItems.getProductName()).build())
                            .retrieve()
                            .bodyToMono(Boolean.class)
                            .block();

                    if (!doesProductExist)
                        throw new IllegalArgumentException("Invaild Product name");
                    return orderLineItems;
                })
                .collect(Collectors.toList());
        orderDTOMapper.updateOrderFromDTO(orderDTO, existingOrder);
        existingOrder.getOrderLineItems().addAll(orderLineItemsList);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @Transactional
    public ResponseEntity<String> deleteOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber);
        orderLineItemsRepository.deleteOrderLineItemsByOrderId(order.getId());
        orderRepository.deleteOrderByOrderNumber(orderNumber);
        return new ResponseEntity<>("success", HttpStatus.NO_CONTENT);
    }

}

