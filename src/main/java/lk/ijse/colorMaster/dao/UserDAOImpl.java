package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl {
    public static boolean validateUser(String username, String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next();
    }
    public static boolean saveUser(String username, String password, String email) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO user VALUES(?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        pstm.setString(3, email);

        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }
}
