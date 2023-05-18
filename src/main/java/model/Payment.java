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
    private Card cardId;
    private double price;
    private String status;
    private Date date;
    private Order orderId;
}
