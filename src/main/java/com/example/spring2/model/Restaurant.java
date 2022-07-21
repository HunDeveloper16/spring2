package com.example.spring2.model;

import com.example.spring2.dto.RestaurantDto;
import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Restaurant {

    // ID가 자동으로 생성 및 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @Column(nullable = false)
    String name; //음식점 이름
    @Column(nullable = false)
    int minOrderPrice; // 최소주문 가격
    @Column(nullable = false)
    int deliveryFee; //기본 배달비
    public Restaurant(RestaurantDto requestDto){
        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

}
