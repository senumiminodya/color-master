package lk.ijse.colorMaster.bo.custom.impl;

import lk.ijse.colorMaster.bo.custom.DeliveryBO;
import lk.ijse.colorMaster.dao.custom.VehicleDAO;
import lk.ijse.colorMaster.dao.custom.impl.VehicleDAOImpl;
import lk.ijse.colorMaster.dto.DeliveryDto;
import lk.ijse.colorMaster.entity.Delivery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryFormBOImpl implements DeliveryBO {
    VehicleDAO vehicleDAO = new VehicleDAOImpl();
    @Override
    public List<DeliveryDto> searchVehicle(String id) throws SQLException, ClassNotFoundException {
        List<Delivery> searchVehicle = vehicleDAO.search(id);
        ArrayList<DeliveryDto> vehicleDTOS = new ArrayList<>();

        for (Delivery vehicle : searchVehicle) {
            vehicleDTOS.add(new DeliveryDto(vehicle.getId(),vehicle.getOwnerName(),vehicle.getOwnerPhoneNo()));
        }

        return vehicleDTOS;
    }

    @Override
    public boolean saveVehicle(DeliveryDto dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(new Delivery(dto.getId(), dto.getOwnerName(), dto.getOwnerPhoneNo()));
    }

    @Override
    public boolean deleteVehicle(String id) throws SQLException, ClassNotFoundException {
        return vehicleDAO.delete(id);
    }

    @Override
    public boolean updateVehicle(DeliveryDto dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Delivery(dto.getId(), dto.getOwnerName(), dto.getOwnerPhoneNo()));
    }

    @Override
    public List<DeliveryDto> getAllVehicle() throws SQLException, ClassNotFoundException {
        List<Delivery> allVehicles = vehicleDAO.getAll();
        ArrayList<DeliveryDto> vehicleDTOS = new ArrayList<>();

        for (Delivery vehicle : allVehicles) {
            vehicleDTOS.add(new DeliveryDto(vehicle.getId(), vehicle.getOwnerName(), vehicle.getOwnerPhoneNo()));
        }

        return vehicleDTOS;
    }
}
