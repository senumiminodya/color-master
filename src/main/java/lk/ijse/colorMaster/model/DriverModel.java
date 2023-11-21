package lk.ijse.colorMaster.model;

import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.CustomerDto;
import lk.ijse.colorMaster.dto.DriverDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverModel {
    public DriverDto searchDriver(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

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
        return dto;
    }
    public boolean saveDriver(DriverDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO driver VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getDriverId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setString(4, dto.getPhoneNo());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
    public boolean deleteDriver(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM driver WHERE driver_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;
    }

    public boolean updateDriver(DriverDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE driver SET name = ?, address = ?, phone_no = ? WHERE driver_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getPhoneNo());
        pstm.setString(4, dto.getDriverId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;
    }
    public List<DriverDto> getAllDriver() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

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
        return dtoList;
    }
}
