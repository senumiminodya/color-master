package lk.ijse.colorMaster.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaintStockDto {
    private String id;
    private String name;
    private String type;
    private String size;
    private int qty;
    private double price;
    private String baseId;
}
