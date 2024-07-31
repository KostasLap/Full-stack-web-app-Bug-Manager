package com.TechProAcademy.MysqlJavaApp.Service;
import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.DTO.BugDTO;


import java.util.List;
import java.util.Optional;

public interface BugsService {
    public Bugs saveBugs(Bugs bug);
    public List<BugDTO> getAllBugs();
    public void deleteBug(int bugID);
    public Optional<BugDTO> getBugById(int bugId);

}
