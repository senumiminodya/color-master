package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface OrderPaintDetailsDAO extends CrudDAO<OrderPaintDetailsDTO>{
    boolean saveOrderDetails(List<OrderPaintDetailsDTO> list) throws SQLException;

    //boolean saveOrderDetail(OrderPaintDetailsDTO ob) throws SQLException;
}
