package com.TechProAcademy.MysqlJavaApp.Service;

import com.TechProAcademy.MysqlJavaApp.DTO.BugReportDTO;
import com.TechProAcademy.MysqlJavaApp.Entity.BugReport;

import java.util.List;

public interface BugReportService {
    public BugReportDTO saveBugReport(BugReportDTO bugReportDTO);
    public List<BugReportDTO> getAllBugReports();
    public void deleteReport(int reportId);
    public void updateResolvedState(int reportId);
    public void updateResolvedStateUnresolved(int reportId);
}
