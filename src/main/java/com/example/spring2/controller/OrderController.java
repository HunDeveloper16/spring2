package com.example.spring2.controller;

import com.example.spring2.dto.*;
import com.example.spring2.model.Food;
import com.example.spring2.model.Order;
import com.example.spring2.model.OrderFood;
import com.example.spring2.repository.OrderRepository;
import com.example.spring2.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @PostMapping("/order/request")
    public OrderResponseDto orderFood(@RequestBody OrderRequestDto dto){ // orderDto -> Long  restaurantId , List<OrderFoodDto> foods;

        return orderService.postOrder(dto);

    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrder(){

        return orderService.showOrders();

        }
    }


