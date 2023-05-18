package model;

import enums.CardType;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Card {
    private int id;
    private CardType type;
    private Date date;
    private String phoneNumber;
    private String password;
}
