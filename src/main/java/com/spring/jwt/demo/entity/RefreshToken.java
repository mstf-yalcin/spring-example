package com.spring.jwt.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode(of = "token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String token;

    private ZonedDateTime expiration;

    @OneToOne
    private User user;

}
