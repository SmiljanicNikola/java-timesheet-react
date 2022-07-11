import React,{useEffect, useState} from 'react'
import ClientService from '../services/ClientService';
import './style.css'

export const NewClientForm = (props) => {
    const [takenProps, setTakenProps] = useState(true);
    const [client, setClient] = useState({})




    function closePopup(){
		setTakenProps(false);
    }

    function saveClient(client){
        /*let newClient ={
            clientName: client.clientName
        }
        ClientService.createClient(client);*/

        closePopup();
    }

    return (
        (takenProps == true)?(
        (props.display == true)?(
        <div>
            <div class="popup">
					<div id="new-member" class="popup-inner">
						<h2>Create new client</h2>
						<ul class="form">
							<li>
								<label>Client name:</label>
								<input type="text" class="in-text" />
							</li>								
							<li>
								<label>Address:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>City:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>Zip/Postal code:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>Country:</label>
								<select>
									<option>Select country</option>
								</select>
							</li>
						</ul>
						<div class="buttons">
							<div class="inner">
								<a onClick={saveClient(client)} class="btn green">Save</a>
							</div>
						</div>
					</div>
				</div>
        </div>):<div></div>):<div></div>
    )
}
