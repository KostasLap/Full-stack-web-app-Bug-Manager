# Bug Tracking System

This repository contains a Java application for managing bug reports using MySQL as the database. The application uses Spring Boot for streamlined development and dependency management.

## Project Structure

The project is organized into several packages and classes:

### Entity Package
- **BugReport**: Represents a bug report.
- **Bugs**: Represents bugs with details like severity.
- **Users**: Represents users of the system.
- **Dependencies**: Represents dependencies between bugs.

### Repository Package
- **BugReportRepository**: Database operations for `BugReport`.
- **BugsRepository**: Database operations for `Bugs`.
- **UsersRepository**: Database operations for `Users`.
- **DependenciesRepository**: Database operations for `Dependencies`.

### Service Package
- **BugReportService**: Interface defining methods for managing bug reports.
- **BugReportServiceImpl**: Implementation of `BugReportService`.
- **BugsService**: Interface defining methods for managing bugs.
- **BugsServiceImpl**: Implementation of `BugsService`.
- **DependenciesService**: Interface defining methods for managing dependencies.
- **DependenciesServiceImpl**: Implementation of `DependenciesService`.
- **UsersService**: Interface defining methods for managing users.
- **UserServiceImpl**: Implementation of `UsersService`.

### DTO Package
- **BugDTO**: Data Transfer Object for `Bugs`.
- **BugReportDTO**: Data Transfer Object for `BugReport`.
- **DependencyDTO**: Data Transfer Object for `Dependencies`.
- **UserDTO**: Data Transfer Object for `Users`.

### Controller Package
- **BugsController**: Handles HTTP requests for bug-related operations.
- **BugsReportController**: Handles HTTP requests for bug report-related operations.
- **DependenciesController**: Handles HTTP requests for dependency-related operations.
- **UsersController**: Handles HTTP requests for user-related operations.

### EntityHelper Class
Provides helper methods to interact with entities, perform CRUD operations, and manage dependencies between bugs.

### CSV Import/Export
Supports CSV import/export functionality for users, bugs, dependencies, and bug reports.

### Spring Boot Configuration
Uses Spring Boot annotations for dependency injection and ORM (Object-Relational Mapping).

## Functionality

### Bug Management
- Create, Read, Update, and Delete bug reports.
- Assign bugs to users.
- Track bug severity and dependencies.

### User Management
- Create and manage users.
- Assign bug reports to users.

### Dependency Management
- Define relationships between bugs to manage dependencies.

### BugReport Class Functionality
- **Creation and Modification**: Create new bug reports with detailed information.
- **State Management**: Track the state of bug reports (resolved/unresolved).
- **Association**: Associate bug reports with bugs and users.
- **Automated State Resolution**: Automatically update bug report states based on resolved dependencies.
- **Find Mutual Dependencies**: Identify mutual dependencies between bugs.

### CSV Import/Export
- **Import**: Users, bugs, dependencies, and bug reports from CSV files.
- **Export**: Data to CSV files for reporting and backup.

## Service Layer Functions

### BugReportService
- `saveBugReport(BugReportDTO bugReportDTO)`: Save a new bug report.
- `getAllBugReports()`: Retrieve all bug reports.
- `deleteReport(int reportId)`: Delete a bug report by ID.
- `updateResolvedState(int reportId)`: Update a bug report as resolved.
- `updateResolvedStateUnresolved(int reportId)`: Update a bug report as unresolved.

### BugsService
- `saveBugs(Bugs bug)`: Save a new bug.
- `getAllBugs()`: Retrieve all bugs.
- `deleteBug(int bugID)`: Delete a bug by ID.
- `getBugById(int bugId)`: Retrieve a bug by ID.

### DependenciesService
- `saveDependency(DependencyDTO dependency)`: Save a new dependency.
- `getAllDependencies()`: Retrieve all dependencies.
- `deleteDependency(int dependencyId)`: Delete a dependency by ID.

### UsersService
- `saveUser(Users user)`: Save a new user.
- `getAllUsers()`: Retrieve all users.
- `deleteUser(int userId)`: Delete a user by ID.
- `getUserById(int userId)`: Retrieve a user by ID.
