package lk.ijse.colorMaster.bo.custom;

import lk.ijse.colorMaster.dto.PaintStockDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaintStockBO {
    boolean deletePaints(String id) throws SQLException, ClassNotFoundException;
    boolean savePaints(PaintStockDto dto) throws SQLException, ClassNotFoundException;
    boolean updatePaints(PaintStockDto dto) throws SQLException, ClassNotFoundException;
    ArrayList<PaintStockDto> searchPaints(String id) throws SQLException, ClassNotFoundException;
    List<PaintStockDto> getAllPaints() throws SQLException, ClassNotFoundException;

}
