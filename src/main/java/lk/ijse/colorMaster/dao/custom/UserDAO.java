package lk.ijse.colorMaster.dao.custom;

import lk.ijse.colorMaster.dao.CrudDAO;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO extends CrudDAO<UserDto> {
    boolean validateUser(String username, String password) throws SQLException;
    //boolean saveUser(String username, String password, String email) throws SQLException;
}
