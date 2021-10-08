package ru.gb.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@Table(name = "carts")

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor

public class Cart {
    @Id
    @GeneratedValue
    protected Long id;
    protected Long countProduct;
    @ManyToOne
    protected Product product;
}