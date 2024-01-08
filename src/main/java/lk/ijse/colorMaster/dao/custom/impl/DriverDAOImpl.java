package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dao.custom.DriverDAO;
import lk.ijse.colorMaster.dto.DriverDto;
import lk.ijse.colorMaster.entity.Driver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverDAOImpl implements DriverDAO {
    @Override
    public List<Driver> search(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM driver WHERE driver_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        DriverDto dto = null;
        if (resultSet.next()) {
            String driverId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String phoneNo = resultSet.getString(4);
            dto = new DriverDto(driverId,name,address,phoneNo);
        }
        return dto;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM driver WHERE driver_id = ?");
        ArrayList<Driver> searchDrivers = new ArrayList<>();
        while (rst.next()) {
            Driver entity = new Driver(rst.getString("driver_id"),rst.getString("name"),rst.getString("address"), rst.getString("phone_no"));
            searchDrivers.add(entity);
        }
        return searchDrivers;
    }
    @Override
    public boolean save(Driver entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO driver VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getDriverId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getPhoneNo());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;*/
        return SQLUtil.execute("INSERT INTO driver VALUES(?, ?, ?, ?)",entity.getDriverId(),entity.getName(),entity.getAddress(),entity.getPhoneNo());
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM driver WHERE driver_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;*/
        return SQLUtil.execute("DELETE FROM driver WHERE driver_id = ?",id);
    }

    @Override
    public boolean update(Driver entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE driver SET name = ?, address = ?, phone_no = ? WHERE driver_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getPhoneNo());
        pstm.setString(4, dto.getDriverId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;*/
        return SQLUtil.execute("UPDATE driver SET name = ?, address = ?, phone_no = ? WHERE driver_id = ?",entity.getName(),entity.getAddress(),entity.getPhoneNo(),entity.getDriverId());
    }
    @Override
    public List<Driver> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM driver";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<DriverDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new DriverDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4)
                    )
            );
        }
        return dtoList;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM driver");
        ArrayList<Driver> getAllDrivers = new ArrayList<>();
        while (rst.next()) {
            Driver entity = new Driver(rst.getString("driver_id"),rst.getString("name"),rst.getString("address"), rst.getString("phone_no"));
            getAllDrivers.add(entity);
        }
        return getAllDrivers;
    }
}
