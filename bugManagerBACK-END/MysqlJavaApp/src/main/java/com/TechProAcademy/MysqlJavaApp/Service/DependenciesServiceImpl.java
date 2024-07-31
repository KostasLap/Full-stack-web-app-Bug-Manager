package com.TechProAcademy.MysqlJavaApp.Service;

import com.TechProAcademy.MysqlJavaApp.DTO.DependencyDTO;
import com.TechProAcademy.MysqlJavaApp.Entity.Dependencies;
import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Repository.DependenciesRepository;
import com.TechProAcademy.MysqlJavaApp.Repository.BugsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TechProAcademy.MysqlJavaApp.DTO.DependencyDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DependenciesServiceImpl implements DependenciesService{

    @Autowired
    private DependenciesRepository dependenciesRepository;

    @Autowired
    private BugsRepository bugRepository;

    @Override
    public DependencyDTO saveDependency(DependencyDTO dependencyDTO) {
        Bugs bugOn = bugRepository.findById(dependencyDTO.getBugOnId()).orElseThrow(() -> new IllegalArgumentException("Invalid bug ID"));
        Bugs dependsOnBug = bugRepository.findById(dependencyDTO.getDependsOnBugId()).orElseThrow(() -> new IllegalArgumentException("Invalid bug ID"));

        Dependencies dependency = new Dependencies(bugOn, dependsOnBug);
        Dependencies savedDependency = dependenciesRepository.save(dependency);

        return convertToDTO(savedDependency);
    }

    @Override
    public List<DependencyDTO> getAllDependencies(){
        return dependenciesRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private DependencyDTO convertToDTO(Dependencies dependency) {
        DependencyDTO dto = new DependencyDTO();
        dto.setDependencyId(dependency.getDependencyId());
        dto.setBugOnId(dependency.getBug().getBugId());
        dto.setDependsOnBugId(dependency.getDependBug().getBugId());
        return dto;
    }

    @Override
    public void deleteDependency(int dependencyId){
       dependenciesRepository.deleteById(dependencyId);
    }
}
