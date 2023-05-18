package model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Payment {
    private int id;
    private int cardId;
    private double price;
    private String status;
    private Date date;
    private int orderId;
}
