package lk.ijse.colorMaster.model;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderDto;

import java.sql.*;
import java.time.LocalDate;

public class OrderModel {
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

    private String splitOrderId(String currentOrderId) {
        if(currentOrderId != null) {
            String[] split = currentOrderId.split("O0");

            int id = Integer.parseInt(split[1]); //01
            id++;
            return "O00" + id;
        } else {
            return "O001";
        }
    }

    private static boolean saveOrder(OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getOrderId());
        pstm.setString(2, dto.getCustomerId());
        pstm.setDouble(3, dto.getTotal());
        pstm.setDate(4, dto.getDate());

        return pstm.executeUpdate() > 0;
    }

    public static boolean saveOrderDetails(OrderDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isOrderSaved = saveOrder(dto);
            if (isOrderSaved){
                boolean isAllSaved = OrderDetailsModel.saveOrderDetails(dto.getCartTmList());
                if (isAllSaved){
                    boolean isAllUpdated = PaintStockModel.updateQty(dto.getCartTmList());
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
