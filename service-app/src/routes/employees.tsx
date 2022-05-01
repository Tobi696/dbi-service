import { useEffect, useState } from "react";
import Navbar from "../components/navbar";
import { ServiceURL } from "../index";

interface Employee {
    id: number;
    name: string;
    longitude: number;
    latitude: number;
}

export default function Employees() {
    const [employees, setEmployees] = useState<Employee[]>([]);
    useEffect(() => {
        (async () => {
            const res = await fetch(`${ServiceURL}/employees`)
            const json = await res.json();
            setEmployees(json);
        })();
    }, []);

    const [name, setName] = useState("");
    const [longitude, setLongitude] = useState(0);
    const [latitude, setLatitude] = useState(0);
    const addEmployee = (e: React.FormEvent<HTMLFormElement>) => {
        (async () => {
            e.preventDefault();
            const newEmployee = { name, longitude, latitude };
            const res = await fetch(`${ServiceURL}/employees`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(newEmployee),
            });
            const json = await res.json();
            setEmployees([...employees, json]);
            alert("Employee added");
        })();
    };

    return (
        <div>
            <h1>Employees</h1>
            <Navbar />
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Longitude</th>
                        <th>Latitude</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employees.map((employee: Employee) => (
                            <tr key={employee.id}>
                                <td>{employee.name}</td>
                                <td>{employee.longitude}</td>
                                <td>{employee.latitude}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
            <h2>Add</h2>
            <form onSubmit={addEmployee} method="POST">
                <p>
                    <label>Name:</label>
                    <input onChange={(e) => setName(e.target.value)} type="text" />
                </p>
                <p>
                    <label>Longitude:</label>
                    <input onChange={(e) => setLongitude(parseFloat(e.target.value))} type="text" />
                </p>
                <p>
                    <label>Latitude:</label>
                    <input type="text" onChange={(e) => setLatitude(parseFloat(e.target.value))} />
                </p>
                <p>
                    <button type="submit">Add</button>
                </p>
            </form>
        </div>
    );
}