package lk.ijse.colorMaster.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaintDetails {
    private String orderNo;
    private String paintId;
    private Date date;
    private Integer qty;
}
