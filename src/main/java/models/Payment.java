package models;

import lombok.*;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Payment {

    private int id;
    private int cardId;
    private int orderId;
    private double price;
    private Timestamp createdDate;
}
