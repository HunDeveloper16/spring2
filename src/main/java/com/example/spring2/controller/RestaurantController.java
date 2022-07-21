package com.example.spring2.controller;

import com.example.spring2.dto.RestaurantDto;
import com.example.spring2.model.Restaurant;
import com.example.spring2.repository.RestaurantRepository;
import com.example.spring2.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.ws.Response;
import java.util.List;

@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository){
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;
    }

    @PostMapping("/restaurant/register")                          // 음식점 등록 , Response값 없음
    public ResponseEntity<Restaurant> postRestaurant(@RequestBody RestaurantDto requestDto){
        Restaurant restaurant = restaurantService.register(requestDto);
        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping("/restaurants")                                     // 음식점 조회
    public ResponseEntity<List<Restaurant>> readRestaurant(){
        List<Restaurant> all = restaurantRepository.findAll();
        return ResponseEntity.ok().body(all);
    }


}
