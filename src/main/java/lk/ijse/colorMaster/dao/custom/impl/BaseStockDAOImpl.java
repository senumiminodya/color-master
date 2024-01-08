package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dao.custom.BaseStockDAO;
import lk.ijse.colorMaster.entity.BaseStock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseStockDAOImpl implements BaseStockDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM base_stock WHERE base_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;*/
        return SQLUtil.execute("DELETE FROM base_stock WHERE base_id = ?",id);

    }

    @Override
    public boolean save(BaseStock entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

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

        return isSaved;*/
        return SQLUtil.execute("SELECT sup_id FROM supplier WHERE name = ?",entity.getId(),entity.getType(),entity.getSupName(),entity.getSize(),entity.getQty(),entity.getPrice());
    }

    @Override
    public boolean update(BaseStock entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

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
        return isUpdated;*/
        return SQLUtil.execute("UPDATE base_stock SET base_type = ?, sup_id = ?, size = ?, qty = ?, price = ? WHERE base_id = ?",entity.getType(),entity.getSupName(),entity.getSize(),entity.getQty(),entity.getPrice(),entity.getId());
    }

    @Override
    public List<BaseStock> search(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM base_stock WHERE base_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        BaseStock entity = null;
        if (resultSet.next()) {
            String baseId = resultSet.getString(1);
            String type = resultSet.getString(2);
            String supId = resultSet.getString(3);
            String size = resultSet.getString(4);
            int qty = resultSet.getInt(5);
            double price = resultSet.getDouble(6);

            entity = new BaseStock(baseId, type, supId, size, qty, price);
        }
        return entity;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM base_stock WHERE base_id = ?",id);
        ArrayList<BaseStock> searchBases = new ArrayList<>();
        while (rst.next()) {
            BaseStock entity = new BaseStock(rst.getString("base_id"),rst.getString("base_type"),rst.getString("sup_id"), rst.getString("size"),rst.getInt("qty"),rst.getDouble("price"));
            searchBases.add(entity);
        }
        return searchBases;
    }

    @Override
    public List<BaseStock> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM base_stock";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<BaseStock> entityList = new ArrayList<>();

        while(resultSet.next()) {
            entityList.add(
                    new BaseStock(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5),
                            resultSet.getDouble(6)
                    )
            );
        }
        return entityList;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM base_stock");
        ArrayList<BaseStock> getAllBases = new ArrayList<>();
        while (rst.next()) {
            BaseStock entity = new BaseStock(rst.getString("base_id"),rst.getString("base_type"),rst.getString("sup_id"), rst.getNString("size"),rst.getInt("qty"),rst.getDouble("price"));
            getAllBases.add(entity);
        }
        return getAllBases;
    }
}
