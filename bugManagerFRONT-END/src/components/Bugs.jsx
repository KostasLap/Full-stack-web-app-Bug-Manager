import { useEffect, useState } from 'react'
import "../componentsCss/Bugs.css"



function Bugs(){

    const [bugs,setBugs] = useState([]);
    const [newBugName,setNewBugName] = useState("");
    const [newBugDescription,setNewBugDescription] = useState("");
    const [newSeverity,setNewSeverity] = useState(1);
    const [showAllBugs,setShowAllBugs] =useState(false);
    


    const [dependencies,setDependencies] = useState([]);

    useEffect(()=>{
        fetch("http://localhost:8080/dependencies/getAll")
        .then(res=>res.json())
        .then((result)=>setDependencies(result))
    },[])


    useEffect(()=>{
        const storedBugs = localStorage.getItem('bugs');
        if (storedBugs>0){
            setBugs(JSON.parse(storedBugs))
        } else {
            fetch("http://localhost:8080/bugs/getAll")
                .then(res=>res.json())
                .then((result)=>{
                    setBugs(result);
                    localStorage.setItem('bugs', JSON.stringify(result));
                })
        }
        
    }
    ,[])

    const handleSubmit = (e)=>{
        e.preventDefault();
    } 

    const handleClick = (e)=>{
        e.preventDefault();
        const bug = { 
            name: newBugName, 
            description: newBugDescription, 
            severity: newSeverity 
        };

        fetch("http://localhost:8080/bugs/add", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(bug)
        }).then(()=>{
            console.log("New bug added");
            fetch("http://localhost:8080/bugs/getAll")
                .then(res=>res.json())
                .then((result)=>{
                    setBugs(result);
                    localStorage.setItem('bugs', JSON.stringify(result));
            }
        )
        })
        
        setNewBugName("");
        setNewBugDescription("");
        setNewSeverity(1);

    }

    

    function toggleShowAllBugs() {
        setShowAllBugs(!showAllBugs);
    }

    const handleDelete = (bugId)=>{
        fetch(`http://localhost:8080/bugs/delete/${bugId}`,{
            method:"DELETE"
        }).then(()=>{
            console.log("bug deleted");
            const updatedBugs = bugs.filter(bug=>bug.bugId !==bugId)
            setBugs(updatedBugs)
            localStorage.setItem('bugs',JSON.stringify(updatedBugs));
            
        });

    }


    function bugAndDependency(bugId){
        const depend = dependencies.find((dependency)=>dependency.bugOnId===bugId)
        if (depend){
            const dependBug = bugs.find((bug)=>bug.bugId===depend.dependsOnBugId)
            return dependBug.name;
        }
        return "Uknown dependency"
        
    }


    return(
        <>
            <h3 id='bug-h3'>Bugs</h3>
            <form id='bug-form' onSubmit={handleSubmit}>
                <input type='text' value={newBugName} placeholder='Enter bug name'
                onChange={(e)=>setNewBugName(e.target.value)}>
                </input>

                <input type='text' value={newBugDescription} 
                placeholder='Enter bug description'
                onChange={(e)=>setNewBugDescription(e.target.value)}>
                </input>

                <input type='number' value={newSeverity} placeholder='Enter severity(1-5)' min={1} max={5}
                onChange={(e)=>setNewSeverity(e.target.value)}>
                </input>

                <button onClick={handleClick} type='submit'>Add Bug</button>
            </form>

            <div>
                <button onClick={toggleShowAllBugs}>
                    {showAllBugs? "Hide":"All Bugs"}
                </button>

                {showAllBugs && (
                    <ol id='bug-ol'>
                        {bugs.map((bug)=>(
                            <li key={bug.bugId}>
                                {bug.name}: {bug.description} (Severity:{bug.severity}), <u>Dependency: {bugAndDependency(bug.bugId)}</u>
                                <button className='delete-button' onClick={()=>handleDelete(bug.bugId)}>Delete</button>

                            </li>
                        ))}
                    </ol>
                )}

                
            </div>
        </>

        
       
    )
}

export default Bugs