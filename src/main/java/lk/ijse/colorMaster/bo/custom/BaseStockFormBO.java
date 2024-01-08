package lk.ijse.colorMaster.bo.custom;

import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.entity.BaseStock;

import java.sql.SQLException;
import java.util.List;

public interface BaseStockFormBO {
    boolean deleteBaseStock(String id) throws SQLException, ClassNotFoundException;
    boolean saveBaseStock(BaseStockDto dto) throws SQLException, ClassNotFoundException;
    boolean updateBaseStock(BaseStockDto dto) throws SQLException, ClassNotFoundException;
    List<BaseStockDto> searchBaseStock(String id) throws SQLException, ClassNotFoundException;
    List<BaseStockDto> getAllBaseStock() throws SQLException, ClassNotFoundException;
}
