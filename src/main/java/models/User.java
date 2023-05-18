package models;

import lombok.*;
import enums.Gender;
import enums.Role;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private int id;
    private String name;
    private String phoneNumber;
    private Role role;
    private Gender gender;
}
