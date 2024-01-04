package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;
import lk.ijse.colorMaster.dto.PaintStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaintStockDAO extends CrudDAO<PaintStockDto> {
    /*
    boolean delete(String id) throws SQLException;


    boolean save(PaintStockDto dto) throws SQLException;


    boolean update(PaintStockDto dto) throws SQLException;


    PaintStockDto search(String id) throws SQLException;


    List<PaintStockDto> getAll() throws SQLException;*/

    boolean updateQty(List<OrderPaintDetailsDTO> list) throws SQLException;

    boolean updateQty(OrderPaintDetailsDTO ob) throws SQLException;
}
