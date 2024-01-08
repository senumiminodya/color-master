package lk.ijse.colorMaster.bo.custom.impl;

import lk.ijse.colorMaster.bo.custom.BaseStockFormBO;
import lk.ijse.colorMaster.dao.custom.BaseStockDAO;
import lk.ijse.colorMaster.dao.custom.impl.BaseStockDAOImpl;
import lk.ijse.colorMaster.dto.BaseStockDto;
import lk.ijse.colorMaster.entity.BaseStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseStockFormBOImpl implements BaseStockFormBO {
    BaseStockDAO baseStockDAO = new BaseStockDAOImpl();
    @Override
    public boolean deleteBaseStock(String id) throws SQLException, ClassNotFoundException {
        return baseStockDAO.delete(id);
    }

    @Override
    public boolean saveBaseStock(BaseStockDto dto) throws SQLException, ClassNotFoundException {
        return baseStockDAO.save(new BaseStock(dto.getId(),dto.getType(),dto.getSupName(),dto.getSize(),dto.getQty(),dto.getPrice()));
    }

    @Override
    public boolean updateBaseStock(BaseStockDto dto) throws SQLException, ClassNotFoundException {
        return baseStockDAO.update(new BaseStock(dto.getId(),dto.getType(),dto.getSupName(),dto.getSize(),dto.getQty(),dto.getPrice()));
    }

    @Override
    public List<BaseStockDto> searchBaseStock(String id) throws SQLException, ClassNotFoundException {
        List<BaseStock> searchBases = baseStockDAO.search(id);
        ArrayList<BaseStockDto> baseStockDTOS = new ArrayList<>();

        for (BaseStock baseStock : searchBases) {
            baseStockDTOS.add(new BaseStockDto(baseStock.getId(),baseStock.getType(),baseStock.getSupName(),baseStock.getSize(),baseStock.getQty(),baseStock.getPrice()));
        }

        return baseStockDTOS;

    }

    @Override
    public List<BaseStockDto> getAllBaseStock() throws SQLException, ClassNotFoundException {
        List<BaseStock> allBases = baseStockDAO.getAll();
        ArrayList<BaseStockDto> baseStockDTOS = new ArrayList<>();

        for (BaseStock baseStock : allBases) {
            baseStockDTOS.add(new BaseStockDto(baseStock.getId(),baseStock.getType(),baseStock.getSupName(),baseStock.getSize(),baseStock.getQty(),baseStock.getPrice()));
        }

        return baseStockDTOS;
    }
}
