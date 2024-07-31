package com.TechProAcademy.MysqlJavaApp.Controller;

import com.TechProAcademy.MysqlJavaApp.DTO.BugReportDTO;
import com.TechProAcademy.MysqlJavaApp.Entity.BugReport;
import com.TechProAcademy.MysqlJavaApp.Service.BugReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bugsReport")
@CrossOrigin
public class BugsReportController {

    @Autowired
    private BugReportService bugReportService;

    @PostMapping("/add")
    public String add(@RequestBody BugReportDTO bugReportDTO){
        bugReportService.saveBugReport(bugReportDTO);
        return "Report added";
    }

    @GetMapping("/getAll")
    public List<BugReportDTO>getAllReports(){
        return bugReportService.getAllBugReports();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteReport(@PathVariable int id){
        bugReportService.deleteReport(id);
        return "Report deleted";
    }

    @PutMapping("/resolve/{id}")
    public String updateToResolved(@PathVariable int id){
        bugReportService.updateResolvedState(id);
        return "Report Update to Resolved";
    }

    @PutMapping("/unresolve/{id}")
    public String updateToUnresolved(@PathVariable int id){
        bugReportService.updateResolvedStateUnresolved(id);
        return "Report Update to Unresolved";
    }
}
