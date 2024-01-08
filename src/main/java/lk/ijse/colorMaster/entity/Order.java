package lk.ijse.colorMaster.entity;

import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private Date date;
    private String customerId;
    private double total;
    private List<OrderPaintDetailsDTO> cartTmList = new ArrayList<>();
}
