package lk.ijse.colorMaster.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseStockDto {
    private String id;
    private String type;
    private String supName;
    private String size;
    private int qty;
    private double price;
}
