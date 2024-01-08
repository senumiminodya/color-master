package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.DeliveryDto;
import lk.ijse.colorMaster.entity.Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO extends CrudDAO<Delivery> {
    /*
    DeliveryDto search(String id) throws SQLException;

    boolean save(DeliveryDto dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    boolean update(DeliveryDto dto) throws SQLException;

    List<DeliveryDto> getAll() throws SQLException;*/
}
