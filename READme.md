# Bug Manager App

The Bug Manager App is a comprehensive system for managing bugs, users, dependencies, and bug reports. It consists of a front-end built with React and a back-end built with Java, Spring Boot, and SQL.

## Project Overview

### Front-End

- **Technology:** React
- **Features:**
  - **AppBar:** Displays the title of the application.
  - **Bugs:** Manages and displays bugs. Allows adding, deleting, and showing bugs along with their dependencies.
  - **Users:** Manages and displays users. Allows adding and deleting users.
  - **Dependencies:** Manages and displays dependencies between bugs. Allows adding and deleting dependencies.
  - **BugReports:** Manages and displays bug reports. Allows adding, resolving, un-resolving, and deleting reports.

### Back-End

- **Technology:** Java, Spring Boot
- **Database:** SQL
- **Features:**
  - **Bugs API:** Endpoints to manage bugs, including adding, deleting, and fetching bugs.
  - **Users API:** Endpoints to manage users, including adding, deleting, and fetching users.
  - **Bug Reports API:** Endpoints to manage bug reports, including adding, resolving, un-resolving, and fetching reports.
  - **Dependencies API:** Endpoints to manage dependencies between bugs, including adding, deleting, and fetching dependencies.

## Project Structure

### Front-End

- **`src/components/`**: Contains React components:
  - `AppBar.jsx`
  - `Bugs.jsx`
  - `Users.jsx`
  - `Dependencies.jsx`
  - `BugReports.jsx`

- **`src/componentsCss/`**: Contains CSS files for styling:
  - `AppBar.css`
  - `Bugs.css`
  - `Users.css`
  - `Dependencies.css`
  - `BugReports.css`

- **`index.html`**: Entry point for the application.
- **`index.js`**: JavaScript entry point for rendering the React app.
- **`main.jsx`**: Main JavaScript file for application logic.
- **`App.js`**: Main React component file.

### Back-End

- **`src/main/java/com/example/bugmanager/`**: Contains Java source code:
  - `BugManagerApplication.java`
  - **`controller/`**: Controllers for handling HTTP requests.
  - **`model/`**: Model classes for database entities.
  - **`repository/`**: Repository interfaces for database operations.
  - **`service/`**: Service classes for business logic.

- **`src/main/resources/`**: Contains configuration files:
  - `application.properties` (Configuration settings for Spring Boot)
  - `schema.sql` (Database schema)

## Getting Started

### Front-End

1. **Setup:**
   - Ensure Node.js and npm are installed.
   - Clone the repository and navigate to the project directory.
   - Install dependencies with `npm install`.

2. **Running the Application:**
   - Start the development server with `npm start`.
   - Open `http://localhost:3000` in your browser.

### Back-End

1. **Setup:**
   - Ensure Java and Maven are installed.
   - Clone the repository and navigate to the project directory.
   - Build the project with `mvn install`.

2. **Running the Application:**
   - Start the application with `mvn spring-boot:run`.
   - The API will be available at `http://localhost:8080`.

## API Endpoints

### Bugs

- **GET** `/bugs/getAll`
- **POST** `/bugs/add`
- **DELETE** `/bugs/delete/{id}`

### Users

- **GET** `/users/getAll`
- **POST** `/users/add`
- **DELETE** `/users/delete/{id}`

### Bug Reports

- **GET** `/bugsReport/getAll`
- **POST** `/bugsReport/add`
- **DELETE** `/bugsReport/delete/{id}`
- **PUT** `/bugsReport/resolve/{id}`
- **PUT** `/bugsReport/unresolve/{id}`

### Dependencies

- **GET** `/dependencies/getAll`
- **POST** `/dependencies/add`
- **DELETE** `/dependencies/delete/{id}`

## Contributing

Contributions are welcome! Please open issues or submit pull requests to help improve the application.

