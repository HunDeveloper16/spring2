package com.example.spring2.service;

import com.example.spring2.dto.RestaurantDto;
import com.example.spring2.model.Restaurant;
import com.example.spring2.repository.RestaurantRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
@Builder
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }


    public Restaurant register(RestaurantDto requestDto){

        if(requestDto.getMinOrderPrice() < 1000 || requestDto.getMinOrderPrice() > 100000){
            throw new IllegalArgumentException("1,000 ~ 100,000원 사이로 입력해주세요.");
        }

        if(requestDto.getMinOrderPrice() % 100 != 0){
            throw new IllegalArgumentException("100원 단위로만 입력이 가능합니다.");
        }

        if(requestDto.getDeliveryFee() < 0 || requestDto.getDeliveryFee() > 10000 ){
            throw new IllegalArgumentException(" 기본 배달비는 0 ~ 10000원 사이입니다.");
        }

        if(requestDto.getDeliveryFee() % 500 != 0){
            throw new IllegalArgumentException("기본 배달비는 500원 단위로만 입력이 가능합니다.");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        return restaurantRepository.save(restaurant);
    }
}
