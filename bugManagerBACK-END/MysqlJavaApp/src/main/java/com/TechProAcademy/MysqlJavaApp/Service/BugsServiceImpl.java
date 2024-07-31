package com.TechProAcademy.MysqlJavaApp.Service;
import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Repository.BugsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TechProAcademy.MysqlJavaApp.DTO.BugDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BugsServiceImpl implements BugsService{

    @Autowired
    private BugsRepository bugsRepository;

    @Override
    public Bugs saveBugs(Bugs bug) {
        return bugsRepository.save(bug);
    }

    @Override
    public List<BugDTO> getAllBugs(){
        return bugsRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteBug(int bugID){
        bugsRepository.deleteById(bugID);
    }

    @Override
    public Optional<BugDTO> getBugById(int bugId){
        return bugsRepository.findById(bugId).map(this::convertToDTO);
    }

    private BugDTO convertToDTO(Bugs bug) {
        BugDTO bugDTO = new BugDTO();
        bugDTO.setBugId(bug.getBugId());
        bugDTO.setName(bug.getName());
        bugDTO.setDescription(bug.getDescription());
        bugDTO.setSeverity(bug.getSeverity());
        return bugDTO;
    }

}
