package com.example.spring2.controller;

import com.example.spring2.dto.FoodDto;
import com.example.spring2.model.Food;
import com.example.spring2.repository.FoodRepository;
import com.example.spring2.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class FoodController {

    private final FoodService foodService;
    private final FoodRepository foodRepository;

    @Autowired
    public FoodController(FoodService foodService, FoodRepository foodRepository){
        this.foodService = foodService;
        this.foodRepository = foodRepository;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public ResponseEntity<Food> postFood(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<FoodDto> requestDto){   //음식 등록
        foodService.register(restaurantId, requestDto);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public ResponseEntity<List<Food>> getFood(@PathVariable Long restaurantId){                                 //메뉴판 조회
        List<Food> foodList = foodRepository.findByRestaurantId(restaurantId);
        return ResponseEntity.ok().body(foodList);

    }

}
