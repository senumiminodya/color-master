package lk.ijse.colorMaster.bo.custom;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dto.DeliveryDto;
import lk.ijse.colorMaster.entity.Delivery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DeliveryBO {
    List<DeliveryDto> searchVehicle(String id) throws SQLException, ClassNotFoundException;
    boolean saveVehicle(DeliveryDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException;
    boolean updateVehicle(DeliveryDto dto) throws SQLException, ClassNotFoundException;
    List<DeliveryDto> getAllVehicle() throws SQLException, ClassNotFoundException;
}
