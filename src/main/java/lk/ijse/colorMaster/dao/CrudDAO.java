package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.dto.BaseStockDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {
    boolean delete(String id) throws SQLException;

    boolean save(T dto) throws SQLException;

    boolean update(T dto) throws SQLException;

    T search(String id) throws SQLException;

    List<T> getAll() throws SQLException;
}
