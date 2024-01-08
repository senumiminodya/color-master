package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dao.custom.OrderPaintDetailsDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderPaintDetailsDAOImpl implements OrderPaintDetailsDAO {
    @Override
    public boolean saveOrderDetails(List<OrderPaintDetailsDTO> list) throws SQLException {
        for (OrderPaintDetailsDTO orderPaintDetailsDTO : list) {
            if (!save(orderPaintDetailsDTO)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(OrderPaintDetailsDTO ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("insert into order_paint_details values(?,?,?)");
        pstm.setString(1, ob.getOrderNo());
        pstm.setString(2, ob.getPaintId());
        pstm.setInt(3, ob.getQty());
        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderPaintDetailsDTO dto) throws SQLException {
        return false;
    }

    @Override
    public List<OrderPaintDetailsDTO> search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderPaintDetailsDTO> getAll() throws SQLException {
        return null;
    }
}
