package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO {
    CustomerDto searchCustomer(String id) throws SQLException;
    boolean saveCustomer(CustomerDto dto) throws SQLException;
    boolean deleteCustomer(String id) throws SQLException;

    boolean updateCustomer(CustomerDto dto) throws SQLException;

    List<CustomerDto> getAllCustomer() throws SQLException;
}
