package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dao.custom.SupplierDAO;
import lk.ijse.colorMaster.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM supplier WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;*/
        return SQLUtil.execute("DELETE FROM supplier WHERE sup_id = ?",id);
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO supplier VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getPhoneNo());
        pstm.setString(4, dto.getProduct());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;*/
        return SQLUtil.execute("INSERT INTO supplier VALUES(?, ?, ?, ?)",entity.getId(),entity.getName(),entity.getPhoneNo(),entity.getProduct());
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE supplier SET name = ?, phone_no = ?, product = ? WHERE sup_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getPhoneNo());
        pstm.setString(3, dto.getProduct());
        pstm.setString(4, dto.getId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;*/
        return SQLUtil.execute("UPDATE supplier SET name = ?, phone_no = ?, product = ? WHERE sup_id = ?",entity.getId(),entity.getName(),entity.getPhoneNo(),entity.getProduct());
    }

    @Override
    public ArrayList<Supplier> search(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier WHERE sup_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        SupplierDto dto = null;
        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String supName = resultSet.getString(2);
            String supPhoneNo = resultSet.getString(3);
            String product = resultSet.getString(4);

            dto = new SupplierDto(supId, supName, supPhoneNo, product);
        }
        return dto;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier WHERE sup_id = ?", id);
        ArrayList<Supplier> searchSuppliers = new ArrayList<>();
        while (rst.next()) {
            Supplier entity = new Supplier(rst.getString("sup_id"),rst.getString("name"),rst.getString("phone_no"), rst.getString("product"));
            searchSuppliers.add(entity);
        }
        return searchSuppliers;
    }
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM supplier";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<SupplierDto> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            dtoList.add(
                    new SupplierDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");
        ArrayList<Supplier> getAllSuppliers = new ArrayList<>();
        while (rst.next()) {
            Supplier entity = new Supplier(rst.getString("sup_id"),rst.getString("name"),rst.getString("phone_no"), rst.getString("product"));
            getAllSuppliers.add(entity);
        }
        return getAllSuppliers;
    }
}
