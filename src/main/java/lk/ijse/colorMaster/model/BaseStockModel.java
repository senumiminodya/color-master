package lk.ijse.colorMaster.model;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.dto.CustomerDto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseStockModel {
    public boolean deleteBase(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM base_stock WHERE base_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;
    }

    public boolean saveBase(BaseStockDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO base_stock VALUES(?, ?, ?, ?, ?, ?)";
        //String sup_id = "SELECT sup_id FROM supplier WHERE name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        //PreparedStatement pstm1 = connection.prepareStatement(sup_id);
        //pstm1.setString(1,dto.getSupName());
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getType());
        pstm.setString(3, dto.getSupName());
        pstm.setString(4, dto.getSize());
        pstm.setInt(5, dto.getQty());
        pstm.setDouble(6, dto.getPrice());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updateBase(BaseStockDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE base_stock SET base_type = ?, sup_id = ?, size = ?, qty = ?, price = ? WHERE base_id = ?";
       // String sup_id = "SELECT sup_id FROM supplier WHERE name = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        //PreparedStatement pstm1 = connection.prepareStatement(sup_id);
        //pstm1.setString(1, dto.getSupName());

        pstm.setString(1, dto.getType());
        pstm.setString(2, dto.getSupName());
        pstm.setString(3, dto.getSize());
        pstm.setInt(4, dto.getQty());
        pstm.setDouble(5, dto.getPrice());
        pstm.setString(6, dto.getId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;
    }

    public BaseStockDto searchBase(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM base_stock WHERE base_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        BaseStockDto dto = null;
        if (resultSet.next()) {
            String baseId = resultSet.getString(1);
            String type = resultSet.getString(2);
            String supId = resultSet.getString(3);
            String size = resultSet.getString(4);
            int qty = resultSet.getInt(5);
            double price = resultSet.getDouble(6);

            dto = new BaseStockDto(baseId, type, supId, size, qty, price);
        }
        return dto;
    }

    public List<BaseStockDto> getAllBases() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM base_stock";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<BaseStockDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new BaseStockDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return dtoList;
    }

}
