package com.TechProAcademy.MysqlJavaApp.Service;

import com.TechProAcademy.MysqlJavaApp.DTO.UserDTO;
import com.TechProAcademy.MysqlJavaApp.Entity.Users;


import java.util.List;
import java.util.Optional;

public interface UsersService {
    public Users saveUser(Users user);
    public List<UserDTO> getAllUsers();
    public void deleteUser(int userId);
    public Optional<UserDTO> getUserById(int userId);
}
