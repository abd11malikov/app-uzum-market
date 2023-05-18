package model;

import lombok.*;

import java.sql.Timestamp;
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
    private Timestamp createdDate;
    private int orderId;
}
