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
public class Food extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private Set<FoodRestaurant> foodRestaurant;

}
