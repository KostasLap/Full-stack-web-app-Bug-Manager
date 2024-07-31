package com.TechProAcademy.MysqlJavaApp.Controller;

import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Service.BugsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.TechProAcademy.MysqlJavaApp.DTO.BugDTO;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/bugs")
@CrossOrigin
public class BugsController {
    @Autowired
    private BugsService bugsService;

    @PostMapping("/add")
    public String add(@RequestBody Bugs bug){
        bugsService.saveBugs(bug);
        return "New Bug added";
    }

    @GetMapping("/getAll")
    public List<BugDTO> getAllBugs(){
        return bugsService.getAllBugs();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBug(@PathVariable int id){
        bugsService.deleteBug(id);
        return "bug deleted";
    }

    @GetMapping("/get/{id}")
    public Optional<BugDTO> getBugById(@PathVariable int id){
        return bugsService.getBugById(id);
    }

}
