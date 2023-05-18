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
    private String phoneNumber;
    private String password;
    private Date createdDate;
    private String color;
    private String brand;
    private String description;
    private String size;

}
