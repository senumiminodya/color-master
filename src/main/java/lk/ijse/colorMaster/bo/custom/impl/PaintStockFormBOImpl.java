package lk.ijse.colorMaster.bo.custom.impl;

import lk.ijse.colorMaster.bo.custom.PaintStockBO;
import lk.ijse.colorMaster.dao.custom.PaintStockDAO;
import lk.ijse.colorMaster.dao.custom.impl.PaintStockDAOImpl;
import lk.ijse.colorMaster.dto.PaintStockDto;
import lk.ijse.colorMaster.entity.PaintStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintStockFormBOImpl implements PaintStockBO {
    PaintStockDAO paintStockDAO = new PaintStockDAOImpl();
    @Override
    public boolean deletePaints(String id) throws SQLException, ClassNotFoundException {
        return paintStockDAO.delete(id);
    }

    @Override
    public boolean savePaints(PaintStockDto dto) throws SQLException, ClassNotFoundException {
        return paintStockDAO.save(new PaintStock(dto.getId(), dto.getName(), dto.getType(), dto.getBaseId(), dto.getSize(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public boolean updatePaints(PaintStockDto dto) throws SQLException, ClassNotFoundException {
        return paintStockDAO.update(new PaintStock(dto.getId(), dto.getName(), dto.getType(), dto.getBaseId(), dto.getSize(), dto.getQty(), dto.getPrice()));
    }

    @Override
    public ArrayList<PaintStockDto> searchPaints(String id) throws SQLException, ClassNotFoundException {
        List<PaintStock> searchPaints = paintStockDAO.search(id);
        ArrayList<PaintStockDto> paintDTOS = new ArrayList<>();

        for (PaintStock paints : searchPaints) {
            paintDTOS.add(new PaintStockDto(paints.getId(),paints.getName(),paints.getType(),paints.getBaseId(),paints.getSize(),paints.getQty(),paints.getPrice()));
        }

        return paintDTOS;
    }

    @Override
    public List<PaintStockDto> getAllPaints() throws SQLException, ClassNotFoundException {
        List<PaintStock> allPaints = paintStockDAO.getAll();
        ArrayList<PaintStockDto> paintDTOS = new ArrayList<>();

        for (PaintStock paints : allPaints) {
            paintDTOS.add(new PaintStockDto(paints.getId(),paints.getName(),paints.getType(),paints.getBaseId(),paints.getSize(),paints.getQty(),paints.getPrice()));
        }

        return paintDTOS;
    }
}
