import React,{useEffect, useState} from 'react'
import ClientService from '../../services/ClientService';
import CountryService from '../../services/CountryService';
import '../../assets/css/popup.css'

export const NewClientForm = (props) => {
	
	const [takenProps, setTakenProps] = useState(props.display);   
	const [client, setClient] = useState({})
	const [display, setDisplay] = useState(false);
	const [countries, setCountries] = useState([])
	const [valueCountry, setValueCountry] = useState('')


	const fetchCountries = () => {
		CountryService.getCountries().then((response) => {
			setCountries(response.data);
		})
	}
	
	const handleChangeCountry = country =>{
		setValueCountry(country.target.value);
		console.log(valueCountry);
	}

    function saveClient(client){
        let newClient ={
			clientName: client.clientName,
			address: client.address,
			city: client.city,
			zipCode: client.zipCode,
			countryId: valueCountry
		}
		console.log(newClient)
        ClientService.createClient(newClient);
    }

    return (
        (props.display == true)?(
        <div>
            <div class="popup">
					<div id="new-member" class="popup-inner">
						<h2>Create new client</h2>
						<ul class="form">
							<li>
								<label>Client name:</label>
								<input type="text" name="clientName" id="clientName" value={client.clientName} onChange={e => setClient({...client, clientName:e.target.value})} class="in-text" />
							</li>								
							<li>
								<label>Address:</label>
								<input name="address" id="address" value={client.address} onChange={e => setClient({...client, address:e.target.value})} type="text" class="in-text" />
							</li>
							<li>
								<label>City:</label>
								<input name="city" id="city" value={client.city} onChange={e => setClient({...client, city:e.target.value})} type="text" class="in-text" />
							</li>
							<li>
								<label>Zip/Postal code:</label>
								<input name="zipCode" id="zipCode" value={client.zipCode} onChange={e => setClient({...client, zipCode:e.target.value})} type="text" class="in-text" />
							</li>
							<li>
								<label>Country:</label>
								<select
									name="country"
									onChange={handleChangeCountry}
									onClick={fetchCountries}
								>
									<option>Select country</option>
									{
										countries.map((country) => 
										(
											<option
												onClick={handleChangeCountry}
												getOptionValue={country => country.id}
												value={country.id}
												key={country.id}
											>{country.name}</option>
										))
									}
								</select>
							</li>
						</ul>
						<div class="buttons" style={{marginTop:'25px'}}>				
								<a onClick={() => saveClient(client)} class="btn green">Save</a>
						</div>
					</div>
				</div>
		</div>
		)
		:
		<div></div>
    )
}
