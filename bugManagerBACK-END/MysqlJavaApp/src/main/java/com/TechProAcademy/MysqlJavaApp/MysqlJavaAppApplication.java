package com.TechProAcademy.MysqlJavaApp;


import com.TechProAcademy.MysqlJavaApp.Entity.BugReport;
import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Entity.Dependencies;
import com.TechProAcademy.MysqlJavaApp.Entity.Users;
import com.TechProAcademy.MysqlJavaApp.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class MysqlJavaAppApplication implements CommandLineRunner {
	@Autowired
	BugsRepository bugsRepository;

	@Autowired
	BugReportRepository bugReportRepository;

	@Autowired
	UsersRepository usersRepository;

	@Autowired
	DependenciesRepository dependenciesRepository;

	@Autowired
	EntityHelper entityHelper;

	public static void main(String[] args) {
		SpringApplication.run(MysqlJavaAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


//		List<Bugs> all = bugsRepository.findAll();
//		for (int i = 0; i<all.size();i++){
//			System.out.println(all);
//
//		}



//		entityHelper.resetAutoIncrementAndDeleteAll();
//
//
//		entityHelper.readAndAddUsersFromCSV("users.csv");
//		entityHelper.readAndAddBugsFromCSV("bugs.csv");
//		entityHelper.readAndAddDependenciesFromCSV("dependencies.csv");
//		entityHelper.readAndAddBugsReportsFromCSV("bugreport.csv");

//		entityHelper.findMutualDependencies();

//		BugReport report = bugReportRepository.findById(2).get();
//		report.setResolved(false);
//		bugReportRepository.save(report);

//		entityHelper.updateStateResolved();




//		Users user = new Users("Ieroklis");
//		usersRepository.save(user);
//
//		Bugs bug = new Bugs();
//		bug.setName("CodError");
//		bug.setDescription("Unfinished match");
//		bug.setSeverity(1);
//		bugsRepository.save(bug);
//
//		Dependencies dep = new Dependencies(bug,bugsRepository.findById(2).get());
//		dependenciesRepository.save(dep);
//
//		BugReport report = new BugReport(bug,user,LocalDateTime.now(),false,null);
//		bugReportRepository.save(report);


//		entityHelper.exportUserCSV();
//		entityHelper.exportBugsCSV();
//		entityHelper.exportBugReportCSV();
//		entityHelper.exportDependenciesCSV();

//entityHelper.noResolvedReportsList();




	}
}
