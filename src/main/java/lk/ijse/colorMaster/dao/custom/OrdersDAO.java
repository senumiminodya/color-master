package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderDto;
import lk.ijse.colorMaster.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrdersDAO extends CrudDAO<OrderDto> {
    String generateNextOrderId() throws SQLException;

    String splitOrderId(String currentOrderId);

    //boolean saveOrder(OrderDto dto) throws SQLException;

    boolean saveOrderDetails(OrderDto dto) throws SQLException;
}
