package lk.ijse.colorMaster.dto.cm;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseCm {
    private String id;
    private String type;
    private String supName;
    private String size;
    private int qty;
    private double price;
}
