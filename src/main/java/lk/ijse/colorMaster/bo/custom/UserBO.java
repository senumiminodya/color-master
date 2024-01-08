package lk.ijse.colorMaster.bo.custom;

import lk.ijse.colorMaster.dao.SQLUtil;
import lk.ijse.colorMaster.db.DbConnection;
import lk.ijse.colorMaster.dto.UserDto;
import lk.ijse.colorMaster.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserBO {
    boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException;
    boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDto dto) throws SQLException;
    List<UserDto> searchUser(String id) throws SQLException;
    List<UserDto> getAllUser() throws SQLException;
}
