package com.TechProAcademy.MysqlJavaApp.Repository;

import com.TechProAcademy.MysqlJavaApp.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUserName(String userName);
}
