package com.TechProAcademy.MysqlJavaApp.Entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bug_report_info")
public class BugReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bug_id", nullable = false)
    private Bugs bug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reporter_id", nullable = false)
    private Users user;

    @Column(name = "date_added")
    private LocalDateTime dateAdded; //maybe can be static with LocalDate.now() but to be more flexible

    @Column(name = "resolved")
    private Boolean resolved;

    @Column(name = "date_resolved")
    private LocalDateTime dateResolved; //maybe can be static with LocalDate.now() but to be more flexible

    public BugReport(Bugs bug, Users user, LocalDateTime dateAdded, Boolean resolved, LocalDateTime dateResolved) {
        this.bug = bug;
        this.user = user;
        this.dateAdded = dateAdded;
        this.resolved = resolved;
        this.dateResolved = dateResolved;
    }

    public BugReport() {
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Bugs getBug() {
        return bug;
    }

    public void setBug(Bugs bug) {
        this.bug = bug;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }

    public LocalDateTime getDateResolved() {
        return dateResolved;
    }

    public void setDateResolved(LocalDateTime dateResolved) {
        this.dateResolved = dateResolved;
    }

    @Override
    public String toString() {
        return "BugReport{" +
                "reportId=" + reportId +
                ", bug=" + bug +
                ", user=" + user +
                ", dateAdded=" + dateAdded +
                ", resolved=" + resolved +
                ", dateResolved=" + dateResolved +
                '}';
    }
}
