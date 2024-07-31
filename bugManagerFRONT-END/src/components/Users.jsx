import { useEffect, useState } from 'react';
import "../componentsCss/Users.css";

function Users() {
    const [users, setUsers] = useState([]);
    const [newUserName, setNewUserName] = useState("");
    const [showAllUsers, setShowAllUsers] = useState(false);

    useEffect(() => {
        const storedUsers = localStorage.getItem('users');
        if (storedUsers.length>0) {
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

    const handleSubmit = (e) => {
        e.preventDefault();
    };

    const handleClick = (e) => {
        e.preventDefault();
        const user = { 
            userName: newUserName, 
        };

        fetch("http://localhost:8080/users/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user)
        }).then(() => {
            console.log("New User added");
            fetch("http://localhost:8080/users/getAll")
                .then(res => res.json())
                .then((result) => {
                    setUsers(result);
                    localStorage.setItem('users', JSON.stringify(result));
                });
        });

        setNewUserName("");
    };

    const handleDelete = (userId) => {
        fetch(`http://localhost:8080/users/delete/${userId}`, {
            method: "DELETE"
        }).then(() => {
            console.log("User deleted");
            const updatedUsers = users.filter(user => user.userId !== userId);
            setUsers(updatedUsers);
            localStorage.setItem('users', JSON.stringify(updatedUsers));
        });
    };

    function toggleShowAllUsers() {
        setShowAllUsers(!showAllUsers);
    }

    return (
        <>
            <h3 id='user-h3'>Users</h3>
            <form id='user-form' onSubmit={handleSubmit}>
                <input
                    type='text'
                    value={newUserName}
                    placeholder='Enter username'
                    onChange={(e) => setNewUserName(e.target.value)}
                />
                <button onClick={handleClick} type='submit'>Add User</button>
            </form>

            <div>
                <button onClick={toggleShowAllUsers}>
                    {showAllUsers ? "Hide" : "All Users"}
                </button>

                {showAllUsers && (
                    <ol id='user-ol'>
                        {users.map((user, index) => (
                            <li key={index}>
                                {user.userName}
                                <button className='delete-button' onClick={() => handleDelete(user.userId)}>Delete</button>
                            </li>
                        ))}
                    </ol>
                )}
            </div>
        </>
    );
}

export default Users;
