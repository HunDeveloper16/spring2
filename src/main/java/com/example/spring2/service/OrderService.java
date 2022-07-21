package com.example.spring2.service;

import com.example.spring2.dto.*;
import com.example.spring2.model.Food;
import com.example.spring2.model.OrderFood;
import com.example.spring2.model.Order;
import com.example.spring2.model.Restaurant;
import com.example.spring2.repository.FoodRepository;
import com.example.spring2.repository.OrderRepository;
import com.example.spring2.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class OrderService {

    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public OrderResponseDto postOrder(OrderRequestDto orderDto) { // orderDto -> Long restaurantId , List<OrderFoodDto> foods;

        Long restaurantId = orderDto.getRestaurantId();
        List<OrderFoodDto> orderFoodDtoList = orderDto.getFoods();

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        List<OrderFoodResponseDto> foodResponseDtoList = new ArrayList<>();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("없는 식당입니다."));

        orderResponseDto.setRestaurantName(restaurant.getName());
        orderResponseDto.setDeliveryFee(restaurant.getDeliveryFee());


        List<OrderFood> orderFoodList = new ArrayList<>();   // Order+Food를 합친 OrderFood 클래스를 리스트로 가지는 orderFoodList 생성

        int totalPrice = 0;

        for (OrderFoodDto orderFoodDto : orderFoodDtoList) {
            Long foodId = orderFoodDto.getId();            // food id가져옴
            int quantity = orderFoodDto.getQuantity();      // food quantity 가져옴

            if (quantity > 100 || quantity < 1) {
                throw new IllegalArgumentException("주문 수량은 1~100 사이만 허용됩니다.");
            }

            Food food = foodRepository.findById(foodId)         // food id를 찾은 뒤, food를 가져옴
                    .orElseThrow(() -> new IllegalArgumentException("없는 음식입니다."));

            int foodPrice = food.getPrice() * quantity;
            totalPrice += foodPrice;

            OrderFood orderFood = new OrderFood(food, quantity);

            orderFoodList.add(orderFood); // food , quantity를 orderFoodList를 넣음
            foodResponseDtoList.add(new OrderFoodResponseDto(food.getName(), quantity, foodPrice));
        }
        Order order = new Order(restaurant, orderFoodList); // order에 restaurant와 orderFoodList를 넣어준다.

        orderResponseDto.setFoods(foodResponseDtoList);
        orderResponseDto.setTotalPrice(totalPrice + restaurant.getDeliveryFee());

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("식당의 최소 주문 금액보다 낮습니다.");
        }

        orderRepository.save(order);
        return orderResponseDto;

    }

    public List<OrderResponseDto> showOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();


        for (Order order : orders) {
            Restaurant restaurant = order.getRestaurant();
            OrderResponseDto orderResponseDto = new OrderResponseDto();
            orderResponseDto.setRestaurantName(order.getRestaurant().getName());
            orderResponseDto.setDeliveryFee(order.getRestaurant().getDeliveryFee());

            List<OrderFoodResponseDto> orderFoodResponseDtoList = new ArrayList<>();
            List<OrderFood> orderFoods = order.getOrderFoods();

            int totalPrice = 0;

            for (OrderFood orderFood : orderFoods) {
                OrderFoodResponseDto orderFoodResponseDto = new OrderFoodResponseDto(
                        orderFood.getFood().getName(),
                        orderFood.getQuantity(),
                        orderFood.getFood().getPrice() * orderFood.getQuantity());

                totalPrice += orderFood.getFood().getPrice() * orderFood.getQuantity();
                orderFoodResponseDtoList.add(orderFoodResponseDto);
            }
            orderResponseDto.setFoods(orderFoodResponseDtoList);
            orderResponseDto.setTotalPrice(totalPrice * restaurant.getDeliveryFee());

            orderResponseDtoList.add(orderResponseDto);

        }
        return orderResponseDtoList;
    }
}

