package com.TechProAcademy.MysqlJavaApp.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "bugs")
public class Bugs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bug_id")
    private int bugId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "severity")
    private Integer severity;

    @OneToMany(mappedBy = "bug", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BugReport> bugReports;

    public Bugs() {
    }

    public Bugs(String name, String description, Integer severity) {
        this.name = name;
        this.description = description;
        if((this.severity = severity)>5){
            this.severity=5;
        }else{
            this.severity=severity;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public Integer getSeverity() {
        return severity;
    }

    public void setSeverity(Integer severity) {
        this.severity = severity;
    }

    public List<BugReport> getBugReports() {
        return bugReports;
    }

    public void setBugReports(List<BugReport> bugReports) {
        this.bugReports = bugReports;
    }

    @Override
    public String toString() {
        return "Bugs{" +
                "bugId=" + bugId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", severity=" + severity +
                '}';
    }
}
