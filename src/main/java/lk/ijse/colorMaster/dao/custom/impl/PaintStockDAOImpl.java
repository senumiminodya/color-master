package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dao.custom.PaintStockDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;
import lk.ijse.colorMaster.dto.PaintStockDto;
import lk.ijse.colorMaster.entity.PaintStock;
import lk.ijse.colorMaster.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintStockDAOImpl implements PaintStockDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM paint_stock WHERE paint_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;*/
        return SQLUtil.execute("DELETE FROM paint_stock WHERE paint_id = ?",id);
    }

    @Override
    public boolean save(PaintStock entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

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

        return isSaved;*/
        return SQLUtil.execute("INSERT INTO paint_stock VALUES(?, ?, ?, ?, ?, ?, ?)",entity.getId(),entity.getName(),entity.getType(),entity.getBaseId(),entity.getSize(),entity.getQty(),entity.getPrice());
    }

    @Override
    public boolean update(PaintStock entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

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
        return isUpdated;*/
        return SQLUtil.execute("UPDATE paint_stock SET name = ?, type = ?, base_id = ?, size = ?, qty = ?, price = ? WHERE paint_id = ?",entity.getName(),entity.getType(),entity.getBaseId(),entity.getSize(),entity.getQty(),entity.getPrice(),entity.getId());
    }

    @Override
    public List<PaintStock> search(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

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

            dto = new PaintStockDto(paintId,name,type,baseId,size,qty,price);
        }
        return dto;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM paint_stock WHERE paint_id = ?",id);
        ArrayList<PaintStock> searchPaints = new ArrayList<>();
        while (rst.next()) {
            PaintStock entity = new PaintStock(rst.getString("paint_id"),rst.getString("name"),rst.getString("type"), rst.getString("base_id"), rst.getString("size"),rst.getInt("qty"),rst.getDouble("price"));
            searchPaints.add(entity);
        }
        return searchPaints;
    }

    @Override
    public List<PaintStock> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM paint_stock";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<PaintStockDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new PaintStockDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getInt(6),
                            resultSet.getDouble(7)

                    )
            );
        }
        return dtoList;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM paint_stock");
        ArrayList<PaintStock> getAllPaints = new ArrayList<>();
        while (rst.next()) {
            PaintStock entity = new PaintStock(rst.getString("paint_id"),rst.getString("name"),rst.getString("type"), rst.getString("base_id"), rst.getString("size"),rst.getInt("qty"),rst.getDouble("price"));
            getAllPaints.add(entity);
        }
        return getAllPaints;
    }

    @Override
    public boolean updateQty(List<OrderPaintDetailsDTO> list) throws SQLException {
        for (OrderPaintDetailsDTO orderPaintDetailsDTO : list) {
            if (!updateQty(orderPaintDetailsDTO)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(OrderPaintDetailsDTO ob) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = null;

        String sql = "UPDATE paint_stock SET qty = qty - ? WHERE paint_id = ?";
        pstm = connection.prepareStatement(sql);
        pstm.setInt(1, ob.getQty());
        pstm.setString(2, ob.getPaintId());
        return pstm.executeUpdate() > 0;
    }
}
