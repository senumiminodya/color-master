package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierDAO extends CrudDAO<SupplierDto> {
    /*
    boolean delete(String id) throws SQLException;


    boolean save(SupplierDto dto) throws SQLException;


    boolean update(SupplierDto dto) throws SQLException;


    SupplierDto search(String id) throws SQLException;


    ArrayList<SupplierDto> getAll() throws SQLException;*/
}
