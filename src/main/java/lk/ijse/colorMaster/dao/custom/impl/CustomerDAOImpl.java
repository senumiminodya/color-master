package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dao.custom.CustomerDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.entity.BaseStock;
import lk.ijse.colorMaster.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> search(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE cus_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        CustomerDto dto = null;
        if (resultSet.next()) {
            String cusId = resultSet.getString(1);
            String cusName = resultSet.getString(2);
            String cusAddress = resultSet.getString(3);
            String cusTelNo = resultSet.getString(4);

            dto = new CustomerDto(cusId, cusName, cusAddress, cusTelNo);
        }
        return dto;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE cus_id = ?", id);
        ArrayList<Customer> searchCustomers = new ArrayList<>();
        while (rst.next()) {
            Customer entity = new Customer(rst.getString("cus_id"),rst.getString("name"),rst.getString("address"), rst.getString("phone_no"));
            searchCustomers.add(entity);
        }
        return searchCustomers;
    }
    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getPhoneNo());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;*/
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?)",entity.getId(),entity.getName(),entity.getAddress(),entity.getPhoneNo());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;*/
        return SQLUtil.execute("DELETE FROM customer WHERE cus_id = ?",id);
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE customer SET name = ?, address = ?, phone_no = ? WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getPhoneNo());
        pstm.setString(4, dto.getId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;*/
        return SQLUtil.execute("UPDATE customer SET name = ?, address = ?, phone_no = ? WHERE cus_id = ?",entity.getName(),entity.getAddress(),entity.getPhoneNo(),entity.getId());
    }

    @Override
    public List<Customer> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CustomerDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new CustomerDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> getAllCustomers = new ArrayList<>();
        while (rst.next()) {
            Customer entity = new Customer(rst.getString("cus_id"),rst.getString("name"),rst.getString("address"), rst.getString("phone_no"));
            getAllCustomers.add(entity);
        }
        return getAllCustomers;

    }
}
