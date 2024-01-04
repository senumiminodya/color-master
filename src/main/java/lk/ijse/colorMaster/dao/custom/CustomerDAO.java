package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerDAO extends CrudDAO<CustomerDto> {
    /*
    CustomerDto search(String id) throws SQLException;

    boolean save(CustomerDto dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(CustomerDto dto) throws SQLException;

    List<CustomerDto> getAll() throws SQLException;*/
}
