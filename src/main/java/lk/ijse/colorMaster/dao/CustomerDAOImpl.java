package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl {
    public CustomerDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

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
        return dto;
    }
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getPhoneNo());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM customer WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;
    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE customer SET name = ?, address = ?, phone_no = ? WHERE cus_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getPhoneNo());
        pstm.setString(4, dto.getId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;
    }

    public List<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

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
        return dtoList;
    }
}
