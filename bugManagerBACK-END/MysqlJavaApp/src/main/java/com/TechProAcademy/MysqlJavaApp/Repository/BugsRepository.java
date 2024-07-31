package com.TechProAcademy.MysqlJavaApp.Repository;


import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface BugsRepository extends JpaRepository<Bugs, Integer> {
    Optional<Bugs> findByName(String name);
//    Bugs findByName(String name); //where name =?
//    List<Bugs> findByNameLike(String name);//where name = %name%

}
