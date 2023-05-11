package com.spring.jwt.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Category extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Food> foods;

}
