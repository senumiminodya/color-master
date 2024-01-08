package lk.ijse.colorMaster.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseStock {
    private String id;
    private String type;
    private String supName;
    private String size;
    private int qty;
    private double price;
}
