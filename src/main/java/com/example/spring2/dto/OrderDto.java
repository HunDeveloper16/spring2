package com.example.spring2.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderDto {

    Long  restaurantId;

    List<OrderFoodDto> foods;

}
