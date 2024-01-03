package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.OrderPaintDetailsDTO;
import lk.ijse.colorMaster.dto.PaintStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaintStockDAO {
    boolean deletePaint(String id) throws SQLException;

    boolean savePaint(PaintStockDto dto) throws SQLException;

    boolean updatePaint(PaintStockDto dto) throws SQLException;

    PaintStockDto searchPaint(String id) throws SQLException;

    List<PaintStockDto> getAllPaints() throws SQLException;

    boolean updateQty(List<OrderPaintDetailsDTO> list) throws SQLException;

    boolean updateQty(OrderPaintDetailsDTO ob) throws SQLException;
}
