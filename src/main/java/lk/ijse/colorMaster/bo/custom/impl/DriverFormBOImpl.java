package lk.ijse.colorMaster.bo.custom.impl;

import lk.ijse.colorMaster.bo.custom.DriverBO;
import lk.ijse.colorMaster.dao.custom.DriverDAO;
import lk.ijse.colorMaster.dao.custom.impl.DriverDAOImpl;
import lk.ijse.colorMaster.dto.DriverDto;
import lk.ijse.colorMaster.entity.Driver;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverFormBOImpl implements DriverBO {
    DriverDAO driverDAO = new DriverDAOImpl();
    @Override
    public List<DriverDto> searchDriver(String id) throws SQLException, ClassNotFoundException {
        List<Driver> searchDrivers = driverDAO.search(id);
        ArrayList<DriverDto> driverDTOS = new ArrayList<>();

        for (Driver driver : searchDrivers) {
            driverDTOS.add(new DriverDto(driver.getDriverId(),driver.getName(),driver.getAddress(),driver.getPhoneNo()));
        }

        return driverDTOS;
    }

    @Override
    public boolean saveDriver(DriverDto dto) throws SQLException, ClassNotFoundException {
        return driverDAO.save(new Driver(dto.getDriverId(), dto.getName(), dto.getAddress(), dto.getPhoneNo()));
    }

    @Override
    public boolean deleteDriver(String id) throws SQLException, ClassNotFoundException {
        return driverDAO.delete(id);
    }

    @Override
    public boolean updateDriver(DriverDto dto) throws SQLException, ClassNotFoundException {
        return driverDAO.update(new Driver(dto.getDriverId(), dto.getName(), dto.getAddress(), dto.getPhoneNo()));
    }

    @Override
    public List<DriverDto> getAllDriver() throws SQLException, ClassNotFoundException {
        List<Driver> allDrivers = driverDAO.getAll();
        ArrayList<DriverDto> driverDTOS = new ArrayList<>();

        for (Driver driver : allDrivers) {
            driverDTOS.add(new DriverDto(driver.getDriverId(), driver.getName(), driver.getAddress(), driver.getPhoneNo()));
        }

        return driverDTOS;
    }
}
