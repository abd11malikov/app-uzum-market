package model;

import enums.CardType;
import lombok.*;


import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private int id;
    private String number;
    private CardType type;
    private int ownerId;
    private String password;
    private double balance;
    private Timestamp date;
}
