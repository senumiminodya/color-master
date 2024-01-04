package lk.ijse.colorMaster.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
    private String userName;
    private String password;
    private String email;
}
