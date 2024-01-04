package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.BaseStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BaseStockDAO extends CrudDAO<BaseStockDto> {
    /*boolean delete(String id) throws SQLException;

    boolean save(BaseStockDto dto) throws SQLException;

    boolean update(BaseStockDto dto) throws SQLException;

    BaseStockDto search(String id) throws SQLException;

    List<BaseStockDto> getAll() throws SQLException;*/
}
