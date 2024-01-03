package lk.ijse.colorMaster.dao;

import lk.ijse.colorMaster.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO {
    boolean validateUser(String username, String password) throws SQLException;
    boolean saveUser(String username, String password, String email) throws SQLException;
}
