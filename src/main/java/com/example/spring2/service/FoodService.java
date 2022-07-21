package com.example.spring2.service;


import com.example.spring2.dto.FoodDto;
import com.example.spring2.model.Food;
import com.example.spring2.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }

    @Transactional
    public void register(Long restaurantId, List<FoodDto> requestDto){

        for(FoodDto requestFood : requestDto) { // 등록 하려는 푸드 ex)쉑 버거 더블, 치즈 감자튀김, 쉐이크...

            Optional<Food> foodList= foodRepository.findByRestaurantIdAndName(restaurantId, requestFood.getName());
            if(foodList.isPresent()){
                throw new IllegalArgumentException("이미 메뉴가 존재합니다.");
            }
            Food food = new Food(restaurantId,requestFood);
            foodRepository.save(food);
        }

        for(FoodDto requestFood : requestDto){                                         // 가격 오류
            if(requestFood.getPrice() < 100 || requestFood.getPrice() > 1000000){
                throw new IllegalArgumentException( "100원 ~ 1,000,000원 사이로 입력해주세요.");
            }

            if(requestFood.getPrice() % 100 != 0){
                throw new IllegalArgumentException(" 100원 단위로 입력해주세요. ");
            }
        }

    }
}
