package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.BaseStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface BaseStockDAO {
    boolean deleteBase(String id) throws SQLException;

    boolean saveBase(BaseStockDto dto) throws SQLException;

    boolean updateBase(BaseStockDto dto) throws SQLException;

    BaseStockDto searchBase(String id) throws SQLException;

    List<BaseStockDto> getAllBases() throws SQLException;
}
