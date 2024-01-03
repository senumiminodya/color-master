package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.OrderPaintDetailsDAO;
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
            if (!saveOrderDetail(orderPaintDetailsDTO)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetail(OrderPaintDetailsDTO ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("insert into order_paint_details values(?,?,?)");
        pstm.setString(1, ob.getOrderNo());
        pstm.setString(2, ob.getPaintId());
        pstm.setInt(3, ob.getQty());
        return pstm.executeUpdate() > 0;
    }
}
