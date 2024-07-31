package com.TechProAcademy.MysqlJavaApp.Repository;

import com.TechProAcademy.MysqlJavaApp.Entity.BugReport;
import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BugReportRepository extends JpaRepository<BugReport, Integer> {
    List<BugReport> findByResolved(boolean resolved);
    List<BugReport> findByBug(Bugs bug);
//    List<BugReport> findByResolved(boolean resolved);
//    BugReport findByUser(Users userId);
//    BugReport findByBugId(Bugs bugId);
//    List<BugReport> findByDateAddedIn(List<Date> dates);
//    List<BugReport> findByDateAddedBefore(Date date);
//    List<BugReport>findByDateAddedAfter(Date date);
//    List<BugReport> findByDateResolvedIn(List<Date> dates);
//    List<BugReport> findByDateResolvedBefore(Date date);
//    List<BugReport>findByDateResolvedAfter(Date date);


}
