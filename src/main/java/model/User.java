package model;

import enums.Gender;
import lombok.*;

import javax.management.relation.Role;

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
