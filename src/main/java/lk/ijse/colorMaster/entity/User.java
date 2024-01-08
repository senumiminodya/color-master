package lk.ijse.colorMaster.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String userName;
    private String password;
    private String email;
}
