package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.DeliveryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO {
    DeliveryDto searchVehicle(String id) throws SQLException;
    boolean saveVehicle(DeliveryDto dto) throws SQLException;
    boolean deleteVehicle(String id) throws SQLException;

    boolean updateVehicle(DeliveryDto dto) throws SQLException;

    List<DeliveryDto> getAllVehicle() throws SQLException;
}
