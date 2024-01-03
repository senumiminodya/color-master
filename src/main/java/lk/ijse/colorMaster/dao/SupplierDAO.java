package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO {
    boolean deleteSupplier(String id) throws SQLException;

    boolean saveSupplier(SupplierDto dto) throws SQLException;

    boolean updateSupplier(SupplierDto dto) throws SQLException;

    SupplierDto searchSupplier(String id) throws SQLException;
    ArrayList<SupplierDto> getAllSupplier() throws SQLException;
}
