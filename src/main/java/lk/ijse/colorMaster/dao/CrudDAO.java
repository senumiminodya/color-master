package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.dto.BaseStockDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> {
    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;

    boolean update(T dto) throws SQLException, ClassNotFoundException;

    List<T> search(String id) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;
}
