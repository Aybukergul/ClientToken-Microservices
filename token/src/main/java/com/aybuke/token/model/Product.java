package com.aybuke.token.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(value = "name")
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @JsonProperty(value = "price")
    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    public Product(){}
    public Product(String name, Double price){
        this.name = name;
        this.price = price;
    }
}
