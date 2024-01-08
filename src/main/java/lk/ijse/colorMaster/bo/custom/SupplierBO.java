package lk.ijse.colorMaster.bo.custom;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dto.SupplierDto;
import lk.ijse.colorMaster.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO {
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException;
    ArrayList<SupplierDto> searchSupplier(String id) throws SQLException, ClassNotFoundException;
    ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException;
}
