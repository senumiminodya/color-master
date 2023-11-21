package lk.ijse.colorMaster.dto;

import lk.ijse.colorMaster.dto.tm.CartTm;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDto {
    private String orderId;
    private Date date;
    private String customerId;
    private double total;
    private List<OrderPaintDetailsDTO> cartTmList = new ArrayList<>();
}
