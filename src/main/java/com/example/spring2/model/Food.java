package com.example.spring2.model;


import com.example.spring2.dto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    Long restaurantId;

    public Food(Long restaurantId, FoodDto requestDto){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = restaurantId;
    }





}
