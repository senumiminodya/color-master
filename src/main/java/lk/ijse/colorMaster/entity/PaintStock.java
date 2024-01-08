package lk.ijse.colorMaster.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PaintStock {
    private String id;
    private String name;
    private String type;
    private String baseId;
    private String size;
    private int qty;
    private double price;
}
