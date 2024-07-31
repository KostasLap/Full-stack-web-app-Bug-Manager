package com.TechProAcademy.MysqlJavaApp.Service;

import com.TechProAcademy.MysqlJavaApp.DTO.BugReportDTO;
import com.TechProAcademy.MysqlJavaApp.Repository.BugReportRepository;
import com.TechProAcademy.MysqlJavaApp.Entity.BugReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Entity.Users;
import com.TechProAcademy.MysqlJavaApp.Repository.UsersRepository;
import com.TechProAcademy.MysqlJavaApp.Repository.BugsRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BugReportServiceImpl implements BugReportService{

    @Autowired
    private BugReportRepository bugReportRepository;

    @Autowired
    private  BugsRepository bugsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public BugReportDTO saveBugReport(BugReportDTO bugReportDTO) {
        Bugs bug = bugsRepository.findById(bugReportDTO.getBugId()).orElseThrow(() -> new IllegalArgumentException("Invalid bug ID"));
        Users user = usersRepository.findById(bugReportDTO.getUserId()).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));


        BugReport bugReport = new BugReport(bug, user, LocalDateTime.now(), false, null);

        BugReport savedReport = bugReportRepository.save(bugReport);

        return convertToDTO(savedReport);
    }

    @Override
    public List<BugReportDTO> getAllBugReports(){
        return bugReportRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    @Override
    public void deleteReport(int reportId){
        bugReportRepository.deleteById(reportId);
    }

    @Override
    public void updateResolvedState(int reportId){
        BugReport report = bugReportRepository.findById(reportId).get();
        report.setResolved(true);
        report.setDateResolved(LocalDateTime.now());
        bugReportRepository.save(report);
    }

    @Override
    public void updateResolvedStateUnresolved(int reportId){
        BugReport report = bugReportRepository.findById(reportId).get();
        report.setResolved(false);
        report.setDateResolved(null);
        bugReportRepository.save(report);
    }

    private BugReportDTO convertToDTO(BugReport bugReport) {
        BugReportDTO dto = new BugReportDTO();
        dto.setReportId(bugReport.getReportId());
        dto.setBugId(bugReport.getBug().getBugId());
        dto.setUserId(bugReport.getUser().getUserId());
        dto.setDateAdded(bugReport.getDateAdded());
        dto.setResolved(bugReport.getResolved());
        dto.setDateResolved(bugReport.getDateResolved());
        return dto;
    }

}
