package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String name;
    private String phoneNumber;
    private String password;
    private String email;
    private String gender;
    private String birthDay;
    private String likes;
}
