package com.spring.jwt.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "food_restaurant")
public class FoodRestaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    @ManyToOne
    @JoinColumn(name = "foodId")
    Food food;

    @ManyToOne
    @JoinColumn(name = "restaurantId")
    Restaurant restaurant;

    @OneToMany(mappedBy = "foodRestaurant", cascade = CascadeType.ALL)
    private Set<Rating> ratings;


}
