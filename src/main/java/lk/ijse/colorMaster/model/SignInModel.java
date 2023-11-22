package lk.ijse.colorMaster.model;

import lk.ijse.colorMaster.db.DbConnection;

import java.sql.*;

public class SignInModel {
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
