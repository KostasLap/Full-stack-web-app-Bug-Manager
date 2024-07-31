# Bug Manager App Frontend

## Overview

The Bug Manager App is a React-based application designed to manage bug reports, bugs, users, and dependencies. It provides an interface for managing bugs, users, dependencies, and bug reports. The front-end communicates with the back-end via REST APIs.

## Components

### AppBar

- **Description:** Displays the title of the application.
- **Functionality:** Provides a header for the application with a simple title.

### BugReports

- **Description:** Manages and displays bug reports.
- **Functionality:**
  - Fetches and displays bug reports from the backend.
  - Allows users to add new bug reports by selecting a bug and a reporter.
  - Provides options to mark bug reports as resolved or unresolved.
  - Enables the deletion of bug reports.
  - Displays statistics about total, resolved, and unresolved reports.

### Bugs

- **Description:** Manages and displays bugs.
- **Functionality:**
  - Fetches and displays bugs from the backend.
  - Allows users to add new bugs with a name, description, and severity.
  - Provides options to delete existing bugs.
  - Displays the dependencies of each bug.

### Dependencies

- **Description:** Manages and displays bug dependencies.
- **Functionality:**
  - Fetches and displays dependencies from the backend.
  - Allows users to add new dependencies between bugs.
  - Provides options to delete existing dependencies.

### Users

- **Description:** Manages and displays users.
- **Functionality:**
  - Fetches and displays users from the backend.
  - Allows users to add new users.
  - Provides options to delete existing users.

## Usage

1. **Setup:**
   - Ensure you have Node.js and npm installed.
   - Clone the repository and navigate to the project directory.
   - Install the required dependencies using `npm install`.

2. **Running the Application:**
   - Start the application using `npm start`.
   - Open `http://localhost:3000` in your browser to access the application.

3. **Endpoints:**
   - **Bugs:** `/bugs/getAll`, `/bugs/add`, `/bugs/delete/{id}`
   - **Users:** `/users/getAll`, `/users/add`, `/users/delete/{id}`
   - **Bug Reports:** `/bugsReport/getAll`, `/bugsReport/add`, `/bugsReport/delete/{id}`, `/bugsReport/resolve/{id}`, `/bugsReport/unresolve/{id}`
   - **Dependencies:** `/dependencies/getAll`, `/dependencies/add`, `/dependencies/delete/{id}`

## Project Structure

- `src/components/AppBar.jsx`: Contains the AppBar component.
- `src/components/BugReports.jsx`: Contains the BugReports component.
- `src/components/Bugs.jsx`: Contains the Bugs component.
- `src/components/Dependencies.jsx`: Contains the Dependencies component.
- `src/components/Users.jsx`: Contains the Users component.
- `src/index.jsx`: Entry point of the application.


## Project Structure

```plaintext
src/
│
├── components/
│   ├── AppBar.jsx
│   ├── Bugs.jsx
│   ├── Users.jsx
│   ├── Dependencies.jsx
│   └── BugReports.jsx
│
├── componentsCss/
│   ├── AppBar.css
│   ├── Bugs.css
│   ├── Users.css
│   ├── Dependencies.css
│   └── BugReports.css
│
├── index.html
├── index.js
|── main.jsx
└── App.js














