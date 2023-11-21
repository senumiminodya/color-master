package lk.ijse.colorMaster.dto.tm;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseStockTm {
    private String id;
    private String type;
    private String supName;
    private String size;
    private int qty;
    private double price;
}
