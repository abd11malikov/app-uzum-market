package model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private int id;
    private String name;
    private double price;
    private int subcategoryId;
    private String description;
    private String color;
    private String size;
    private int ownerId;
    private int amount;

}
