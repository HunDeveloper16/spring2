package com.example.spring2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RestaurantDto {

    Long id;

    String name; //음식점 이름

    int minOrderPrice; // 최소주문 가격

    int deliveryFee; //기본 배달비

}
