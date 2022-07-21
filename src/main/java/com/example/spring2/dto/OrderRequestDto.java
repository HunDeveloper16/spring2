package com.example.spring2.dto;

import com.example.spring2.model.Food;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {

    private Long restaurantId;

    private List<OrderFoodDto> foods;

}
