package com.TechProAcademy.MysqlJavaApp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dependencies")
public class Dependencies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dependency_id")
    private Integer dependencyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bug_id", nullable = false)
    private Bugs bug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "depend_on_bug_id", nullable = false)
    private Bugs dependBug;

    public Dependencies(Bugs bug, Bugs dependBug) {
        this.bug = bug;
        this.dependBug = dependBug;
    }

    public Dependencies() {
    }

    public Integer getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        this.dependencyId = dependencyId;
    }

    public Bugs getBug() {
        return bug;
    }

    public void setBug(Bugs bug) {
        this.bug = bug;
    }

    public Bugs getDependBug() {
        return dependBug;
    }

    public void setDependBug(Bugs dependBug) {
        this.dependBug = dependBug;
    }

    public void getIntegerOfBugId(Bugs bug){
        bug.getBugId();
    }

    @Override
    public String toString() {
        return "Dependencies{" +
                "dependencyId=" + dependencyId +
                ", bug=" + bug +
                ", dependBug=" + dependBug +
                '}';
    }
}
