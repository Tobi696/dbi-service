import { useEffect, useState } from "react";
import Navbar from "../components/navbar";

interface Service {
    id: number;
    name: string;
    employeeId: number;
    date: string;
    longitude: number;
    latitude: number;
}

export default function Services() {
    const [services, setServices] = useState<Service[]>([]);
    useEffect(() => {
        (async () => {
            const response = await fetch(`http://localhost:8080/serviceBackend/services`);
            const data = await response.json();
            setServices(data);
        })();
    }, []);

    const [editingId, setEditingId] = useState<number | null>(null);
    const [name, setName] = useState("");
    const [employeeId, setEmployeeId] = useState(0);
    const [date, setDate] = useState("");
    const [longitude, setLongitude] = useState(0);
    const [latitude, setLatitude] = useState(0);

    const submitService = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const newService = { name, employeeId, date, longitude, latitude };
        console.log(latitude);
        (async () => {
            const response = await fetch(`http://localhost:8080/serviceBackend/services` + (editingId == null ? '' : `/${editingId}`), {
                method: editingId == null ? "POST" : "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(newService),
            });
            const data = await response.json();
            if (editingId == null) {
                setServices([...services, data]);
                alert("Service added");
            } else {
                setServices(services.map(service => service.id === data.id ? data : service));
                alert("Service edited");
            }
            setEditingId(null);
            setName("");
            setEmployeeId(0);
            setDate("");
            setLongitude(0);
            setLatitude(0);
        })();
    };

    const editService = (service: Service) => {
        if (editingId === service.id) {
            console.log("here");
            setEditingId(null);
            setName("");
            setEmployeeId(0);
            setDate("");
            setLongitude(0);
            setLatitude(0);
            return;
        }
        if (!service) return;
        setEditingId(service.id);
        setName(service.name);
        setEmployeeId(service.employeeId);
        setDate(service.date);
        setLongitude(service.longitude);
        setLatitude(service.latitude);
    };

    const deleteService = (service: Service) => {
        (async () => {
            await fetch(`http://localhost:8080/serviceBackend/services/${service.id}`, {
                method: "DELETE",
            });
            setServices(services.filter(s => s.id !== service.id));
            alert("Service deleted");
        })();
    };

    const getAddress = (service: Service) => {
        (async () => {
            const response = await fetch(`http://localhost:8080/serviceBackend/services/${service.id}/address`);
            const text = await response.text();
            alert(text);
        })();
    }

    return (
        <div>
            <h1>Services</h1>
            <Navbar />
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Employee</th>
                        <th>Date</th>
                        <th>Longitude</th>
                        <th>Latitude</th>
                        <th>Address</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    {services.map((service) => (
                        <tr key={service.id}>
                            <td>{service.name}</td>
                            <td>{service.employeeId}</td>
                            <td>{service.date}</td>
                            <td>{service.longitude}</td>
                            <td>{service.latitude}</td>
                            <td>
                                <button onClick={() => getAddress(service)}>Get Address</button>
                            </td>
                            <td>
                                <button onClick={() => editService(service)} style={{ backgroundColor: editingId == service.id ? "darkGrey" : undefined }}>Update</button>
                            </td>
                            <td>
                                <button onClick={() => deleteService(service)}>Delete</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <form onSubmit={submitService}>
                <p>
                    <label>Name</label>
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
                </p>
                <p>
                    <label>Employee</label>
                    <input type="text" value={employeeId} onChange={(e) => setEmployeeId(parseInt(e.target.value))} />
                </p>
                <p>
                    <label>Date</label>
                    <input type="text" value={date} onChange={(e) => setDate(e.target.value)} />
                </p>
                <p>
                    <label>Latitude</label>
                    <input type="text" value={latitude} onChange={(e) => setLatitude(parseFloat(e.target.value))} />
                </p>
                <p>
                    <label>Longitude</label>
                    <input type="text" value={longitude} onChange={(e) => setLongitude(parseFloat(e.target.value))} />
                </p>
                <p>
                    <button type="submit">{editingId == null ? "Add" : "Update"}</button>
                </p>
            </form>
        </div>
    );
}