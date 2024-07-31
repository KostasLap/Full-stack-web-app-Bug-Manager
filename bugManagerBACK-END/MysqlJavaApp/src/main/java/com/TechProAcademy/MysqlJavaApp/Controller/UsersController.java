package com.TechProAcademy.MysqlJavaApp.Controller;

import com.TechProAcademy.MysqlJavaApp.DTO.UserDTO;
import com.TechProAcademy.MysqlJavaApp.Entity.Users;
import com.TechProAcademy.MysqlJavaApp.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/add")
    public String add(@RequestBody Users user){
        usersService.saveUser(user);
        return "New User added";

    }

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers(){
        return usersService.getAllUsers();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
        usersService.deleteUser(id);
        return "User deleted";
    }

    @GetMapping("/get/{id}")
    public Optional<UserDTO>getUserById(@PathVariable int id){
        return usersService.getUserById(id);
    }


}
