package com.spring.jwt.demo.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.ZonedDateTime;

@MappedSuperclass
@Getter
@Setter
public class  BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    @CreatedDate
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
//    @Temporal(value = TemporalType.TIMESTAMP)
    private ZonedDateTime createdAt;

//    @LastModifiedDate
    @UpdateTimestamp
//    @Temporal(value = TemporalType.TIMESTAMP)
    private ZonedDateTime updateAt;

    private Boolean isDeleted = false;
}
