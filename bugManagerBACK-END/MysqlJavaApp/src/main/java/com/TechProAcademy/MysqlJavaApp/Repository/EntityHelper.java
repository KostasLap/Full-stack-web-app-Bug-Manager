package com.TechProAcademy.MysqlJavaApp.Repository;

import com.TechProAcademy.MysqlJavaApp.Entity.BugReport;
import com.TechProAcademy.MysqlJavaApp.Entity.Bugs;
import com.TechProAcademy.MysqlJavaApp.Entity.Dependencies;
import com.TechProAcademy.MysqlJavaApp.Entity.Users;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;



import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

@Component
public class EntityHelper {

    @Autowired
    private BugsRepository bugsRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private DependenciesRepository dependenciesRepository;

    @Autowired
    private BugReportRepository bugReportRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Users findOrCreateUser(String userName) {
        Optional<Users> existingUser = usersRepository.findByUserName(userName);
        if (existingUser.isPresent()) {
            return existingUser.get();
        } else {
            Users newUser = new Users(userName);
            return usersRepository.save(newUser);
        }
    }

    public Bugs findOrCreateBug(String name, String description, Integer severity) {
        Optional<Bugs> existingBug = bugsRepository.findByName(name);
        if (existingBug.isPresent()) {
            return existingBug.get();
        } else {
            Bugs newBug = new Bugs(name, description, severity);
            return bugsRepository.save(newBug);
        }
    }

    public Dependencies findOrCreateDependency(Bugs bug, Bugs dependBug) {
        Optional<Dependencies> existingDependency = dependenciesRepository.findByBugAndDependBug(bug, dependBug);
        if (existingDependency.isPresent()) {
            return existingDependency.get();
        } else {
            Dependencies newDependency = new Dependencies(bug, dependBug);
            return dependenciesRepository.save(newDependency);
        }
    }

    public  BugReport createBugReportAndUserAndBug(String bugName, String bugDescription,Integer bugSeverity,String dependOnBug,String userName){
        Bugs bug = findOrCreateBug(bugName,bugDescription,bugSeverity);
        Bugs dependBug = findOrCreateBug(dependOnBug,null,null);
        Users user = findOrCreateUser(userName);
        Dependencies dependency = findOrCreateDependency(bug,dependBug);

        BugReport report = new BugReport(bug,user, LocalDateTime.now(),false,null);
        return bugReportRepository.save(report);
    }

    public void updateStateResolved(){
        List <BugReport> bugReports = bugReportRepository.findByResolved(false);
        for (BugReport bugReport : bugReports ){
            Bugs bug = bugReport.getBug();//bug
            Optional<Dependencies> depOptional = dependenciesRepository.findByBug(bug);
            if(depOptional.isPresent()){
                Dependencies dependency = depOptional.get();
                Bugs dependBug = dependency.getDependBug();
                List<BugReport> dependOnBugReports = bugReportRepository.findByBug(dependBug);

                if(!dependOnBugReports.isEmpty()){
                    BugReport dependOnBugReport = dependOnBugReports.get(0);
                    if(dependOnBugReport.getResolved()){
                        bugReport.setResolved(true);
                        bugReport.setDateResolved(LocalDateTime.now());
                        bugReportRepository.save(bugReport);
                        updateStateResolved();
                    }
                }
            }

       }

    }

    public void noResolvedReportsList(){
        List<BugReport> noResolvedReports = bugReportRepository.findByResolved(false);
        if (!noResolvedReports.isEmpty()){
            for(BugReport noResolvedReport:noResolvedReports ){
                System.out.println(noResolvedReport);
            }
        }

    }

    public void resolvedReportsList(){
        List<BugReport>resolvedReports = bugReportRepository.findByResolved(true);
        if(!resolvedReports.isEmpty()){
            for (BugReport resolvedReport: resolvedReports){
                System.out.println(resolvedReport);
            }
        }

    }

    public void allReportsList(){
        List<BugReport> allReports = bugReportRepository.findAll();
        if(!allReports.isEmpty()){
            for (BugReport report:allReports){
                System.out.println(report);
            }
        }
    }

    public void findMutualDependencies(){
        List<Dependencies> allDependencies = dependenciesRepository.findAll();
        Map<Integer,Integer> dependencies = new HashMap<>();
        for(Dependencies dep:allDependencies){
            dependencies.put(dep.getBug().getBugId(),dep.getDependBug().getBugId());
        }

        Set <List<Integer>> finalWithNoDuplicates = new HashSet<>();
        for(Integer bugId: dependencies.keySet()){
            Set<Integer> alreadyVisited = new HashSet<>();
            List<Integer> mutual = new ArrayList<>();
            Integer currentBug = bugId;

            while(currentBug!=null && !alreadyVisited.contains(currentBug)){
                alreadyVisited.add(currentBug);
                mutual.add(currentBug);
                currentBug = dependencies.get(currentBug);
            }

            if (currentBug !=null && alreadyVisited.contains(currentBug)){
                Collections.sort(mutual);
                finalWithNoDuplicates.add(mutual);
            }
        }
        System.out.println(finalWithNoDuplicates);
    }

    public void exportUserCSV(){
        List<Users> users = usersRepository.findAll();
        String output="user_id,user_name\n";
        for(Users user:users){
            output+= user.getUserId()+","+user.getUserName()+"\n";
        }
        try(FileWriter csvfile = new FileWriter("users.csv")){
            csvfile.write(output);
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void exportBugsCSV(){
        List<Bugs> bugs = bugsRepository.findAll();
        String output = "bug_id,description,name,severity\n";
        for(Bugs bug:bugs){
            output+=bug.getBugId()+","+bug.getDescription()+","+bug.getName()+","+bug.getSeverity()+"\n";

        }
        try(FileWriter csvfile = new FileWriter("bugs.csv")){
            csvfile.write(output);
        } catch (IOException e){
            System.out.println(e);
        }
    }
    public  void exportDependenciesCSV(){
        List<Dependencies> dependencies = dependenciesRepository.findAll();
        String output = "dependency_id,bug_id,depend_on_bug_id\n";
        for (Dependencies dependency:dependencies){
            output+=dependency.getDependencyId()+","+dependency.getBug().getBugId()+","+dependency.getDependBug().getBugId()+"\n";
        }
        try(FileWriter csvfile = new FileWriter("dependencies.csv")){
            csvfile.write(output);
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void exportBugReportCSV(){
        List<BugReport> bugreports = bugReportRepository.findAll();
        String output = "report_id,date_added,date_resolved,resolved,bug_id,reporter_id\n";
        for (BugReport report:bugreports){
            output+=report.getReportId()+","+report.getDateAdded()+","+report.getDateResolved()+","+report.getResolved()+","+report.getBug().getBugId()+","+report.getUser().getUserId()+"\n";
        }
        try(FileWriter csvfile = new FileWriter("bugreport.csv")) {
            csvfile.write(output);
        } catch (IOException e){
            System.out.println(e);
        }
    }

    public void readAndAddUsersFromCSV(String filename){
        try(Reader reader = new FileReader(filename)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("user_id","user_name").withSkipHeaderRecord());

            for (CSVRecord record:csvParser){
                if(!usersRepository.findByUserName(record.get("user_name")).isPresent()){
                    Users user = new Users();
                    user.setUserName(record.get("user_name"));
                    usersRepository.save(user);
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void readAndAddBugsFromCSV(String filename){
        try(Reader reader = new FileReader(filename)) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader("bug_id","description","name","severity").withSkipHeaderRecord());

            for(CSVRecord record:csvParser){
                if (!bugsRepository.findByName(record.get("name")).isPresent()){
                    Bugs bug = new Bugs();
                    bug.setDescription(record.get("description"));
                    bug.setName(record.get("name"));
                    bug.setSeverity(Integer.parseInt(record.get("severity")));
                    bugsRepository.save(bug);
                }

            }
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void readAndAddDependenciesFromCSV(String filename){
        try(Reader reader = new FileReader(filename);){
            CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT.withHeader("dependency_id","bug_id","depend_on_bug_id").withSkipHeaderRecord());

            for(CSVRecord record:csvParser){
                if(!dependenciesRepository.findByBugAndDependBug(bugsRepository.findById(Integer.parseInt(record.get("bug_id"))).get(),bugsRepository.findById(Integer.parseInt(record.get("depend_on_bug_id"))).get()).isPresent()){
                    Dependencies depend = new Dependencies();
                    depend.setBug(bugsRepository.findById(Integer.parseInt(record.get("bug_id"))).get());
                    depend.setDependBug(bugsRepository.findById(Integer.parseInt(record.get("depend_on_bug_id"))).get());
                    dependenciesRepository.save(depend);
                }
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void readAndAddBugsReportsFromCSV(String filename){
        try(Reader reader = new FileReader(filename);){
            CSVParser csvParser = new CSVParser(reader,CSVFormat.DEFAULT.withHeader("report_id","date_added","date_resolved","resolved","bug_id","reporter_id").withSkipHeaderRecord());
            for(CSVRecord record:csvParser){
                BugReport bugReport = new BugReport();
                bugReport.setDateAdded(LocalDateTime.parse(record.get("date_added")));
                if("null".equals(record.get("date_resolved"))){
                    bugReport.setDateResolved(null);
                }else{
                    bugReport.setDateResolved(LocalDateTime.parse(record.get("date_resolved")));
                }
                bugReport.setResolved(Boolean.parseBoolean(record.get("resolved")));
                bugReport.setBug(bugsRepository.findById(Integer.parseInt(record.get("bug_id"))).get());
                bugReport.setUser(usersRepository.findById(Integer.parseInt(record.get("reporter_id"))).get());

                bugReportRepository.save(bugReport);
            }
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public  void resetAutoIncrementAndDeleteAll(){
        bugReportRepository.deleteAll();
        dependenciesRepository.deleteAll();
        bugsRepository.deleteAll();
        usersRepository.deleteAll();

        jdbcTemplate.execute("ALTER TABLE bug_report_info AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE dependencies AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE bugs AUTO_INCREMENT = 1");
        jdbcTemplate.execute("ALTER TABLE users AUTO_INCREMENT = 1");
    }






}
