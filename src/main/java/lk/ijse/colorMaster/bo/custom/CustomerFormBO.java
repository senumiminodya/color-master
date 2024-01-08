package lk.ijse.colorMaster.bo.custom;

import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerFormBO {
    ArrayList<CustomerDto> searchCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException;
    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
}
