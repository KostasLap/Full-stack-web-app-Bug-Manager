import { useEffect, useState } from 'react';
import "../componentsCss/Dependencies.css";

function Dependencies() {
    const [bugs, setBugs] = useState([]);
    const [dependencies, setDependencies] = useState([]);
    const [showAllDependencies, setShowAllDependencies] = useState(false);
    const [bugOn, setBugOn] = useState("");
    const [dependOnBug, setDependOnBug] = useState("");

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
        const storedDependencies = localStorage.getItem('dependencies');
        if (storedDependencies>0) {
            setDependencies(JSON.parse(storedDependencies));
        } else {
            fetch("http://localhost:8080/dependencies/getAll")
                .then(res => res.json())
                .then((result) => {
                    setDependencies(result);
                    localStorage.setItem('dependencies', JSON.stringify(result));
                });
        }
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    const handleClick = (e) => {
        e.preventDefault();

        const tempBugOn = bugs.find((bug) => bug.name === bugOn);
        const tempDependOn = bugs.find((bug) => bug.name === dependOnBug);

        if (tempBugOn && tempDependOn) {
            const dependency = {
                bugOnId: tempBugOn.bugId,
                dependsOnBugId: tempDependOn.bugId
            };

            fetch("http://localhost:8080/dependencies/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(dependency)
            }).then(() => {
                console.log("Dependency added");

                fetch("http://localhost:8080/dependencies/getAll")
                    .then(res => res.json())
                    .then((result) => {
                        setDependencies(result);
                        localStorage.setItem('dependencies', JSON.stringify(result));
                    });
            });

            setBugOn("");
            setDependOnBug("");
        } else {
            console.error("Bug names not found");
        }
    };

    function toggleShowAllDependencies() {
        setShowAllDependencies(!showAllDependencies);
    }

    function handleDelete(dependencyId) {
        fetch(`http://localhost:8080/dependencies/delete/${dependencyId}`, {
            method: "DELETE"
        }).then(() => {
            console.log("Dependency deleted");

            const updatedDependencies = dependencies.filter(dependency => dependency.dependencyId !== dependencyId);
            setDependencies(updatedDependencies);
            localStorage.setItem('dependencies', JSON.stringify(updatedDependencies));
        });
    }

    return (
        <>
            <h3 id='dependency-h3'>Dependencies</h3>
            <form id='dependency-form' onSubmit={handleSubmit}>
                <input
                    type='text'
                    value={bugOn}
                    placeholder='Dependent bug (bug name)'
                    onChange={(e) => setBugOn(e.target.value)}
                />
                <input
                    type='text'
                    value={dependOnBug}
                    placeholder='Blocking bug (bug name)'
                    onChange={(e) => setDependOnBug(e.target.value)}
                />
                <button onClick={handleClick} type='submit'>Add Dependency</button>
            </form>

            <div>
                <button onClick={toggleShowAllDependencies}>{showAllDependencies ? "Hide" : "All dependencies"}</button>
                {showAllDependencies && (
                    <ol id='dependency-ol'>
                        {dependencies.map((dependency, index) => {
                            const bugOnName = bugs.find((bug) => bug.bugId === dependency.bugOnId)?.name || "Unknown";
                            const dependOnBugName = bugs.find((bug) => bug.bugId === dependency.dependsOnBugId)?.name || "Unknown";
                            return (
                                <li key={index}>
                                    {bugOnName} depends on {dependOnBugName}
                                    <button className='delete-button' onClick={() => handleDelete(dependency.dependencyId)}>Delete</button>
                                </li>
                            );
                        })}
                    </ol>
                )}
            </div>
        </>
    );
}

export default Dependencies;
