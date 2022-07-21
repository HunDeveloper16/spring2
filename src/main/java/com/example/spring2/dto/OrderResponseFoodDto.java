package com.example.spring2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseFoodDto {

    String name;

    int quantity;

    int price;


}
