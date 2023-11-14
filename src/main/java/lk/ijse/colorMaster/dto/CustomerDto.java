package lk.ijse.colorMaster.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {
    private String id;
    private String name;
    private String address;
    private String phoneNo;
}
