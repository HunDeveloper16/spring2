package com.example.spring2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class OrderFood {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @ManyToOne
    @JoinColumn(name = "food_id")
    Food food;
    @ManyToOne
    @JoinColumn(name="order_id")
    Order order;
    int quantity;
    public OrderFood(Food food,int quantity){
        this.food = food;
        this.quantity = quantity;
    }

}
