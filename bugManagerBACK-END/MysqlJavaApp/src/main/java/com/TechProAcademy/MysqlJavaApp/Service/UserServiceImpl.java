package com.TechProAcademy.MysqlJavaApp.Service;

import com.TechProAcademy.MysqlJavaApp.DTO.UserDTO;
import com.TechProAcademy.MysqlJavaApp.Repository.UsersRepository;

import com.TechProAcademy.MysqlJavaApp.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users saveUser(Users user){
        return usersRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers(){
        return usersRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public void deleteUser(int userId){
        usersRepository.deleteById(userId);
    }

    @Override
    public Optional<UserDTO> getUserById(int userId){
        return usersRepository.findById(userId).map(this::convertToDTO);

    }



    private UserDTO convertToDTO(Users user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserName(user.getUserName());
        return userDTO;
    }

}
