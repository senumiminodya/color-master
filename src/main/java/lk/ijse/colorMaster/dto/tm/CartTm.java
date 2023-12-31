package lk.ijse.colorMaster.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartTm {
    private String id;
    private String name;
    private double price;
    private int qty;
    private double total;
    private Button action;
}
