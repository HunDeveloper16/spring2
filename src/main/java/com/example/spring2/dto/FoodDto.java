package com.example.spring2.dto;


import lombok.*;


@Setter
@Getter
@NoArgsConstructor
public class FoodDto {

    Long id;

    String name;

    int price;

//    Long restaurantId;      // PathValue로 받기때문에 Dto로 받을필요 X

}
