package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.VehicleDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.DeliveryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public DeliveryDto searchVehicle(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehicle WHERE vehicle_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        DeliveryDto dto = null;
        if (resultSet.next()) {
            String vehicleId = resultSet.getString(1);
            String ownerName = resultSet.getString(2);
            String phoneNo = resultSet.getString(3);

            dto = new DeliveryDto(vehicleId, ownerName, phoneNo);
        }
        return dto;
    }
    @Override
    public boolean saveVehicle(DeliveryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO vehicle VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getOwnerName());
        pstm.setString(3, dto.getOwnerPhoneNo());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
    @Override
    public boolean deleteVehicle(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM vehicle WHERE vehicle_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;
    }

    @Override
    public boolean updateVehicle(DeliveryDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE vehicle SET owner_name = ?, owner_phone_no = ? WHERE vehicle_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getOwnerName());
        pstm.setString(2, dto.getOwnerPhoneNo());
        pstm.setString(3, dto.getId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;
    }

    @Override
    public List<DeliveryDto> getAllVehicle() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM vehicle";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<DeliveryDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new DeliveryDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    )
            );
        }
        return dtoList;
    }
}
