package lk.ijse.colorMaster.dto.tm;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemTm {
    private String id;
    private String name;
    private String type;
    private String size;
    private int qty;
    private double price;
    private String baseId;
}
