package lk.ijse.colorMaster.model;

import lk.ijse.colorMaster.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static boolean validateUser(String username, String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet resultSet = pstm.executeQuery();
        return resultSet.next();
    }
}
