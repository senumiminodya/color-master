package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.custom.OrdersDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrdersDAOImpl implements OrdersDAO {
    @Override
    public String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT order_no FROM orders ORDER BY order_no DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    @Override
    public String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean save(OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getOrderId());
        pstm.setString(2, dto.getCustomerId());
        pstm.setDouble(3, dto.getTotal());
        pstm.setDate(4, dto.getDate());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException {
        return false;
    }

    @Override
    public OrderDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<OrderDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean saveOrderDetails(OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isOrderSaved = save(dto);
            if (isOrderSaved){
                OrderPaintDetailsDAOImpl orderPaintDetailsDAO = new OrderPaintDetailsDAOImpl();
                boolean isAllSaved = orderPaintDetailsDAO.saveOrderDetails(dto.getCartTmList());
                if (isAllSaved){
                    PaintStockDAOImpl paintStockDAO = new PaintStockDAOImpl();
                    boolean isAllUpdated = paintStockDAO.updateQty(dto.getCartTmList());
                    if (isAllUpdated){
                        connection.commit();
                        return true;
                    }

                }
            }

            connection.rollback();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
