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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String token;

    private ZonedDateTime expiration;

    @OneToOne
    @JoinColumn(nullable = false,name = "userId")
    private User user;

}
