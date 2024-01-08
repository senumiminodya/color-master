package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.DriverDto;
import lk.ijse.colorMaster.entity.Driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DriverDAO extends CrudDAO<Driver> {
    /*
    DriverDto search(String id) throws SQLException;

    boolean save(DriverDto dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(DriverDto dto) throws SQLException;

    List<DriverDto> getAll() throws SQLException;*/
}
