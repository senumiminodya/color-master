package lk.ijse.colorMaster.bo.custom.impl;

import lk.ijse.colorMaster.bo.custom.SupplierBO;
import lk.ijse.colorMaster.dao.custom.SupplierDAO;
import lk.ijse.colorMaster.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.colorMaster.dto.SupplierDto;
import lk.ijse.colorMaster.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierFormBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = new SupplierDAOImpl();

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getId(), dto.getName(), dto.getPhoneNo(), dto.getProduct()));
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(), dto.getName(), dto.getPhoneNo(), dto.getProduct()));
    }

    @Override
    public ArrayList<SupplierDto> searchSupplier(String id) throws SQLException, ClassNotFoundException {
        List<Supplier> searchSuppliers = supplierDAO.search(id);
        ArrayList<SupplierDto> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : searchSuppliers) {
            supplierDTOS.add(new SupplierDto(supplier.getId(),supplier.getName(),supplier.getPhoneNo(), supplier.getProduct()));
        }

        return supplierDTOS;
    }

    @Override
    public ArrayList<SupplierDto> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<Supplier> allSuppliers = supplierDAO.getAll();
        ArrayList<SupplierDto> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : allSuppliers) {
            supplierDTOS.add(new SupplierDto(supplier.getId(),supplier.getName(),supplier.getPhoneNo(), supplier.getProduct()));
        }

        return supplierDTOS;
    }
}
