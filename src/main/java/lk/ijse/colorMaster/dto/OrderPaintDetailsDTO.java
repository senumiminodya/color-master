package lk.ijse.colorMaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPaintDetailsDTO {
    private String orderNo;
    private String paintId;
    private Date date;
    private Integer qty;
}
