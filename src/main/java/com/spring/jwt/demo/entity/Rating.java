package com.spring.jwt.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Rating extends BaseEntity {
    @ManyToOne
    private FoodRestaurant foodRestaurant;
    @ManyToOne
    private User user;

    @Column(nullable = false)
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 100, message = "Rating cannot be greater than 100")
    private int rating;


}
