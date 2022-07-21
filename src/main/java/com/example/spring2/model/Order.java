package com.example.spring2.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Getter
@Setter
@Table(name="orders") // SQl에서 Order는 이미 사용하고 있을수 있음 (order by) 따라서 이름을 바꿔줌
@Entity
public class Order { //order와 foods사이는 manytomany

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @ManyToOne //한 레스토랑에대해 여러개의 주문
    @JoinColumn(name = "restaurant_id")
    Restaurant restaurant;


    //Order_Food에서 id를 가져야한다.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //주문하나에여러개의 OrderFood -> 장바구니
    List<OrderFood> orderFoods = new ArrayList<>();
    public Order(Restaurant restaurant, List<OrderFood> orderFoods){
        this.restaurant = restaurant;
        this.orderFoods = orderFoods;
    }

}
//    @Column(nullable = false)
//    @OneToMany
//    List<Food_Order> foods;
//
//    @Column(nullable = false)
//    String restaurantName;
//
//    @Column(nullable = false)
//    int deliveryFee;
//
//    @Column(nullable = false)
//    int totalPrice;


