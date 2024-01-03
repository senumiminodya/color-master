package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderPaintDetailsDAO {
    boolean saveOrderDetails(List<OrderPaintDetailsDTO> list) throws SQLException;

    boolean saveOrderDetail(OrderPaintDetailsDTO ob) throws SQLException;
}
