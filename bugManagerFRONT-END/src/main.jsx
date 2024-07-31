import React from 'react'
import ReactDOM from 'react-dom/client'
import AppBar from './components/AppBar.jsx'
import Bugs from './components/Bugs.jsx'
import Users from './components/Users.jsx'
import Dependencies from './components/Dependencies.jsx'
import BugReports from './components/BugReports.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AppBar />
    <Bugs/>
    <Users/>
    <Dependencies/>
    <BugReports/>

  </React.StrictMode>,
)
