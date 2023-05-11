package com.spring.jwt.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Restaurant extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "restaurant",targetEntity = FoodRestaurant.class, cascade = CascadeType.ALL)
    private Set<FoodRestaurant> foodRestaurant;
    @ManyToOne()
    private User user;

}
