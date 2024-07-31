package com.TechProAcademy.MysqlJavaApp.Service;

import com.TechProAcademy.MysqlJavaApp.DTO.DependencyDTO;
import com.TechProAcademy.MysqlJavaApp.DTO.UserDTO;
import com.TechProAcademy.MysqlJavaApp.Entity.Dependencies;

import java.util.List;
import java.util.Optional;

public interface DependenciesService {
    public DependencyDTO saveDependency(DependencyDTO dependency);
    public List<DependencyDTO>getAllDependencies();
    public void deleteDependency(int dependencyId);

}
