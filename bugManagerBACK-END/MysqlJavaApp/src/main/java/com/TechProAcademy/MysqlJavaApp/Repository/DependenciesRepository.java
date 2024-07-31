package com.TechProAcademy.MysqlJavaApp.Repository;

import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Entity.Dependencies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DependenciesRepository extends JpaRepository<Dependencies, Integer> {
    Optional<Dependencies> findByBugAndDependBug(Bugs bug, Bugs dependBug);
    Optional<Dependencies> findByBug(Bugs bug);
    Optional<Dependencies> findByDependBug(Bugs bug);


}
