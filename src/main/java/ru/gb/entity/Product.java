package ru.gb.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor

public class Product {
    @Id
    @GeneratedValue
    long id;
    String name;
    String description;
    @ManyToMany
    List<Category> categories;

}
