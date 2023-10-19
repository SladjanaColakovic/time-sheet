import { useState } from "react";
import { postRequest } from "../requests/httpClient";

const New = () => {

    const url = "http://localhost:8080/api/category";

    const [name, setName] = useState('');

    const handleSave = () => {
        let data = {
            name: name
        }
        postRequest(url, data)
            .then((res) => {
                console.log(res)
                window.location.reload()
            })
            .catch((error) => {
                console.log(error)
            })
    }


    return (
        <div className='span-top'>
            <div className='row'>
                <div className='col-4'></div>
                <div className='col-4'>
                    <div className="row">
                        <label>Name</label>
                    </div>
                    <div className="row">
                        <input type="text" placeholder="Enter name..." value={name} onChange={(e) => { setName(e.target.value) }} />
                    </div>
                    <br />
                    <div className="row">
                        <button onClick={handleSave} className="save">Save</button>
                    </div>
                </div>
                <div className='col-4'></div>
            </div>
        </div>
    );
}

export default New;