package com.example.spring2.dto;

import lombok.Data;

@Data
public class OrderFoodResponseDto {

    String name;

    int quantity;
    // 음식 금액 * quantity
    int price;


    public OrderFoodResponseDto(String name, int quantity, int foodPrice) {
        this.name = name;
        this.quantity = quantity;
        this.price = foodPrice;

    }
}
