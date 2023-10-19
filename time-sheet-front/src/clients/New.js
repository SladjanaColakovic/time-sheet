import { useState } from "react";
import { postRequest } from "../requests/httpClient";

const New = ({countries}) => {

    const [country, setCountry] = useState(1);
    const [name, setName] = useState('');
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [postalCode, setPostalCode] = useState('');

    const handleSave = () => {
        console.log(country)
        let data = {
            name: name,
            address: address,
            city: city,
            postalCode: postalCode,
            country: {
                id: country
            }
        }
        console.log(data)

        postRequest("http://localhost:8080/api/client", data)
            .then((res) => {
                console.log(res.data)
                window.location.reload()
            })
            .catch((error) => {
                console.log(error.message)
            })
    }

    return ( 
        <div className='span-top'>
            <div className="row">
                <div className="col-4"></div>
                <div className="col-4">
                    <label>Name</label>
                    <input type="text" placeholder="Enter name..." value={name} onChange={(e) => {setName(e.target.value)}}/>
                    <label>Adrress</label>
                    <input type="text" placeholder="Enter address..." value={address} onChange={(e) => {setAddress(e.target.value)}}/>
                    <label>City</label>
                    <input type="text" placeholder="Enter city..." value={city} onChange={(e) => {setCity(e.target.value)}}/>
                    <label>Postal code</label>
                    <input type="text" placeholder="Enter postal code..." value={postalCode} onChange={(e) => {setPostalCode(e.target.value)}}/>
                    <label>Country</label>
                    <select onChange={(e) => { setCountry(e.target.value) }}>
                        {countries && countries.map((country) => (
                            <option key={country.id} value={country.id}>{country.name}</option>
                        ))}
                    </select>
                    <br />
                    <br />
                    <div className="row">
                        <div className="col-4"></div>
                        <div className="col-4">
                            <button onClick={handleSave} className="save">Save</button>
                        </div>
                        <div className="col-4"></div>
                    </div>
                </div>
                <div className="col-4"></div>
            </div>
        </div>
     );
}
 
export default New;