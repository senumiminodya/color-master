package lk.ijse.colorMaster.dao.custom.impl;

import lk.ijse.colorMaster.dao.custom.UserDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean validateUser(String username, String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next();
    }
    /*@Override
    public boolean saveUser(String username, String password, String email) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO user VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, email);

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }*/

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean save(UserDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO user VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, dto.getUserName());
        pstm.setString(2, dto.getPassword());
        pstm.setString(3, dto.getEmail());

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }

    @Override
    public boolean update(UserDto dto) throws SQLException {
        return false;
    }

    @Override
    public UserDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public List<UserDto> getAll() throws SQLException {
        return null;
    }
}
