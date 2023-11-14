package lk.ijse.colorMaster.model;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.dto.PaintStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaintStockModel {
    public boolean deletePaint(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM paint_stock WHERE paint_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;
    }

    public boolean savePaint(PaintStockDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO paint_stock VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getType());
        pstm.setString(4, dto.getBaseId());
        pstm.setString(5, dto.getSize());
        pstm.setInt(6, dto.getQty());
        pstm.setDouble(7, dto.getPrice());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    public boolean updatePaint(PaintStockDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE paint_stock SET name = ?, type = ?, base_id = ?, size = ?, qty = ?, price = ? WHERE paint_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getType());
        pstm.setString(3, dto.getBaseId());
        pstm.setString(4, dto.getSize());
        pstm.setInt(5, dto.getQty());
        pstm.setDouble(6, dto.getPrice());
        pstm.setString(7, dto.getId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;
    }

    public PaintStockDto searchPaint(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM paint_stock WHERE paint_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        PaintStockDto dto = null;
        if (resultSet.next()) {
            String paintId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String type = resultSet.getString(3);
            String baseId = resultSet.getString(4);
            String size = resultSet.getString(5);
            int qty = resultSet.getInt(6);
            double price = resultSet.getDouble(7);

            dto = new PaintStockDto(paintId,name,type,size,qty,price,baseId);
        }
        return dto;
    }
}
