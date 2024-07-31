package com.TechProAcademy.MysqlJavaApp.Controller;

import com.TechProAcademy.MysqlJavaApp.DTO.DependencyDTO;
import com.TechProAcademy.MysqlJavaApp.Entity.Dependencies;
import com.TechProAcademy.MysqlJavaApp.Service.DependenciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dependencies")
@CrossOrigin
public class DependenciesController {

    @Autowired
    private DependenciesService dependenciesService;

    @PostMapping("/add")
    public String add(@RequestBody DependencyDTO dependencyDTO){
        dependenciesService.saveDependency(dependencyDTO);
        return "Dependency added";
    }

    @GetMapping("/getAll")
    public List<DependencyDTO> getAllDependencies(){
        return dependenciesService.getAllDependencies();
    }

    @DeleteMapping("delete/{id}")
    public String deleteDependency(@PathVariable int id){
        dependenciesService.deleteDependency(id);
        return "dependency deleted";
    }
}
