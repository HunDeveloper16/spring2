package com.example.spring2.dto;

import com.example.spring2.model.Food;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {

    private List<OrderFoodResponseDto> foods;

    private String restaurantName;

    private int deliveryFee;

    private int totalPrice;

}
