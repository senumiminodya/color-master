package lk.ijse.colorMaster.bo.custom;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dto.DriverDto;
import lk.ijse.colorMaster.entity.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DriverBO {
    List<DriverDto> searchDriver(String id) throws SQLException, ClassNotFoundException;
    boolean saveDriver(DriverDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteDriver(String id) throws SQLException, ClassNotFoundException;
    boolean updateDriver(DriverDto dto) throws SQLException, ClassNotFoundException;
    List<DriverDto> getAllDriver() throws SQLException, ClassNotFoundException;
}
