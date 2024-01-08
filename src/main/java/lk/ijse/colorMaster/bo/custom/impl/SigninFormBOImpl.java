package lk.ijse.colorMaster.bo.custom.impl;

import lk.ijse.colorMaster.bo.custom.UserBO;
import lk.ijse.colorMaster.dao.custom.impl.UserDAOImpl;
import lk.ijse.colorMaster.dto.UserDto;
import lk.ijse.colorMaster.entity.User;

import java.sql.SQLException;
import java.util.List;

public class SigninFormBOImpl implements UserBO {
    UserDAOImpl userDAO = new UserDAOImpl();
    @Override
    public boolean validateUser(String username, String password) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUserName(), dto.getPassword(), dto.getEmail()));
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException {
        return false;
    }

    @Override
    public List<UserDto> searchUser(String id) throws SQLException {
        return null;
    }

    @Override
    public List<UserDto> getAllUser() throws SQLException {
        return null;
    }
}
