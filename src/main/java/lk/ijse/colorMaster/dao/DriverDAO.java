package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.DriverDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DriverDAO {
    DriverDto searchDriver(String id) throws SQLException;
    boolean saveDriver(DriverDto dto) throws SQLException;
    boolean deleteDriver(String id) throws SQLException;

    boolean updateDriver(DriverDto dto) throws SQLException;
    List<DriverDto> getAllDriver() throws SQLException;
}
