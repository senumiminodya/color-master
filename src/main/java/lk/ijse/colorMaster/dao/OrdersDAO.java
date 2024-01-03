package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrdersDAO {
    String generateNextOrderId() throws SQLException;

    String splitOrderId(String currentOrderId);

    boolean saveOrder(OrderDto dto) throws SQLException;

    boolean saveOrderDetails(OrderDto dto) throws SQLException;
}
