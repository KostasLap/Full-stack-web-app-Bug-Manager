import { useEffect, useState } from 'react';
import "../componentsCss/BugReports.css";

function BugReports() {
    const [users, setUsers] = useState([]);
    const [bugs, setBugs] = useState([]);
    const [bugReports, setBugReports] = useState([]);
    const [bugName, setBugName] = useState("");
    const [reporterName, setReporterName] = useState("");
    const [showReportsButton, setShowReportsButton] = useState(false);

    useEffect(() => {
        const storedBugs = localStorage.getItem('bugs');
        if (storedBugs>0) {
            setBugs(JSON.parse(storedBugs));
        } else {
            fetch("http://localhost:8080/bugs/getAll")
                .then(res => res.json())
                .then((result) => {
                    setBugs(result);
                    localStorage.setItem('bugs', JSON.stringify(result));
                });
        }
    }, []);

    useEffect(() => {
        const storedUsers = localStorage.getItem('users');
        if (storedUsers>0) {
            setUsers(JSON.parse(storedUsers));
        } else {
            fetch("http://localhost:8080/users/getAll")
                .then(res => res.json())
                .then((result) => {
                    setUsers(result);
                    localStorage.setItem('users', JSON.stringify(result));
                });
        }
    }, []);

    useEffect(() => {
        const storedBugReports = localStorage.getItem('bugReports');
        if (storedBugReports>0) {
            setBugReports(JSON.parse(storedBugReports));
        } else {
            fetch("http://localhost:8080/bugsReport/getAll")
                .then(res => res.json())
                .then((result) => {
                    setBugReports(result);
                    localStorage.setItem('bugReports', JSON.stringify(result));
                });
        }
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    const handleClick = (e) => {
        e.preventDefault();

        const bug = bugs.find((bug) => bug.name === bugName);
        const user = users.find((user) => user.userName === reporterName);

        if (bug && user) {
            const report = {
                bugId: bug.bugId,
                userId: user.userId
            };

            fetch("http://localhost:8080/bugsReport/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(report)
            }).then(() => {
                console.log("New Report added");

                fetch("http://localhost:8080/bugsReport/getAll")
                    .then(res => res.json())
                    .then((result) => {
                        setBugReports(result);
                        localStorage.setItem('bugReports', JSON.stringify(result));
                    });
            });

            setBugName("");
            setReporterName("");
        } else {
            console.error("Bug or User not found");
        }
    };

    function toggleShowReportButton() {
        setShowReportsButton(!showReportsButton);
    }

    function handleDeleteReport(reportId) {
        fetch(`http://localhost:8080/bugsReport/delete/${reportId}`, {
            method: "DELETE"
        }).then(() => {
            console.log("Report deleted");

            const updatedBugReports = bugReports.filter(report => report.reportId !== reportId);
            setBugReports(updatedBugReports);
            localStorage.setItem('bugReports', JSON.stringify(updatedBugReports));
        });
    }

    function handleResolved(reportId) {
        const updatedBugReports = bugReports.map(report => {
            if (report.reportId === reportId) {
                return {
                    ...report,
                    resolved: true,
                    dateResolved: new Date().toLocaleDateString()
                };
            }
            return report;
        });

        setBugReports(updatedBugReports);
        localStorage.setItem('bugReports', JSON.stringify(updatedBugReports));

        fetch(`http://localhost:8080/bugsReport/resolve/${reportId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                resolved: true,
                dateResolved: new Date().toISOString()
            })
        }).then(() => {
            console.log("Bug resolved status updated");
        }).catch(error => {
            console.error("Error updating bug resolved status:", error);
        });
    }

    function handleUnresolved(reportId) {
        const updatedBugReports = bugReports.map(report => {
            if (report.reportId === reportId) {
                return {
                    ...report,
                    resolved: false,
                    dateResolved: null
                };
            }
            return report;
        });

        setBugReports(updatedBugReports);
        localStorage.setItem('bugReports', JSON.stringify(updatedBugReports));

        fetch(`http://localhost:8080/bugsReport/unresolve/${reportId}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                resolved: false,
                dateResolved: null
            })
        }).then(() => {
            console.log("Bug resolved status updated to Unresolved");
        }).catch(error => {
            console.error("Error updating bug resolved status:", error);
        });
    }

    return (
        <>
            <h3 id='bugReports-h3'>Bug Reports</h3>

            <form id='bugReports-form' onSubmit={handleSubmit}>
                <input
                    type='text'
                    value={bugName}
                    placeholder='Enter bug name'
                    onChange={(e) => setBugName(e.target.value)}
                />
                <input
                    type='text'
                    value={reporterName}
                    placeholder='Enter reporter Name'
                    onChange={(e) => setReporterName(e.target.value)}
                />
                <button type='submit' onClick={handleClick}>Add Report</button>
            </form>

            <div>
                <button onClick={toggleShowReportButton}>
                    {showReportsButton ? "Hide" : "All Reports"}
                </button>

                {showReportsButton && (
                    <ol id='bugReports-ol'>
                        {bugReports.map((report, index) => (
                            <li key={index}>
                                ID: {report.reportId} <br />
                                Bug Name: {bugs.find((bug) => bug.bugId === report.bugId)?.name || "Unknown"} <br />
                                Date added: {new Date(report.dateAdded).toLocaleString()} <br />
                                Reporter: {users.find(user => user.userId === report.userId)?.userName || "Unknown"} <br />
                                State: {report.resolved ? "Resolved" : "Unresolved"} <br />
                                {report.resolved ? `Date resolved: ${new Date(report.dateResolved).toLocaleString()}` : null} <br />
                                {!report.resolved && (
                                    <button onClick={() => handleResolved(report.reportId)}>
                                        Set bug RESOLVED
                                    </button>
                                )}
                                {report.resolved && (
                                    <button onClick={() => handleUnresolved(report.reportId)}>Set Bug UNRESOLVED</button>
                                )}
                                <button className='delete-button' onClick={() => handleDeleteReport(report.reportId)}>Delete</button>
                            </li>
                        ))}
                    </ol>
                )}
            </div>

            <div>
                <p>Brief report infos</p>
                Total reports: {bugReports.length} <br />
                Resolved reports: {bugReports.filter((report) => report.resolved).length} <br />
                Unresolved reports: {bugReports.filter((report) => !report.resolved).length}
            </div>
        </>
    );
}

export default BugReports;
