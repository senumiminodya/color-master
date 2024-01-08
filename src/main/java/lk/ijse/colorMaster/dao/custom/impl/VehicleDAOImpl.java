package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.dao.custom.VehicleDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.DeliveryDto;
import lk.ijse.colorMaster.entity.Customer;
import lk.ijse.colorMaster.entity.Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public List<Delivery> search(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

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
        return dto;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM vehicle WHERE vehicle_id = ?");
        ArrayList<Delivery> searchDrivers = new ArrayList<>();
        while (rst.next()) {
            Delivery entity = new Delivery(rst.getString("vehicle_id"),rst.getString("owner_name"),rst.getString("owner_phone_no"));
            searchDrivers.add(entity);
        }
        return searchDrivers;
    }
    @Override
    public boolean save(Delivery entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO vehicle VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getOwnerName());
        pstm.setString(3, dto.getOwnerPhoneNo());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;*/
        return SQLUtil.execute("INSERT INTO vehicle VALUES(?, ?, ?)",entity.getId(),entity.getOwnerName(),entity.getOwnerPhoneNo());

    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM vehicle WHERE vehicle_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        boolean isDeleted = pstm.executeUpdate()>0;
        return isDeleted;*/
        return SQLUtil.execute("DELETE FROM vehicle WHERE vehicle_id = ?",id);
    }

    @Override
    public boolean update(Delivery entity) throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE vehicle SET owner_name = ?, owner_phone_no = ? WHERE vehicle_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getOwnerName());
        pstm.setString(2, dto.getOwnerPhoneNo());
        pstm.setString(3, dto.getId());

        boolean isUpdated = pstm.executeUpdate()>0;
        return isUpdated;*/
        return SQLUtil.execute("UPDATE vehicle SET owner_name = ?, owner_phone_no = ? WHERE vehicle_id = ?",entity.getId(),entity.getOwnerName(),entity.getOwnerPhoneNo());
    }

    @Override
    public List<Delivery> getAll() throws SQLException, ClassNotFoundException {
        /*Connection connection = DbConnection.getInstance().getConnection();

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
        return dtoList;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM vehicle");
        ArrayList<Delivery> getAllVehicles = new ArrayList<>();
        while (rst.next()) {
            Delivery entity = new Delivery(rst.getString("vehicle_id"),rst.getString("owner_name"),rst.getString("owner_phone_no"));
            getAllVehicles.add(entity);
        }
        return getAllVehicles;
    }
}
