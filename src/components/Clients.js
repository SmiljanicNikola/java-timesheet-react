import React, { useState, useEffect } from 'react'
import ClientService from '../services/ClientService';
import TeamMemberService from '../services/TeamMemberService';
import axios from 'axios'

export const Clients = () => {
    const [clients, setClients] = useState([]);
    const [pageNumber, setPageNumber] = useState(0);

    const pageSize = 2;

    
    useEffect(() => {
        /*ClientService.getClientsPaginate().then((response) => setClients(response.data))
        console.log(clients)*/
        console.log(pageNumber);
        axios.get("http://localhost:8080/api/clients/paginate?page="+pageNumber+"&size=1")
        .then(response => {
            setClients(response.data.content);
            console.log(response.data.content)
        })
        
        
    }, []);

    function reRender(){
        axios.get("http://localhost:8080/api/clients/paginate?page="+pageNumber+"&size=1")
        .then(response => {
            setClients(response.data.content);
            console.log(response.data.content)
        })
    }



    return (
        <div>
           <div class="wrapper">
			<section class="content">
				<h2><i class="ico clients"></i>Clients</h2>
				<div class="grey-box-wrap reports">
					<a href="#new-member" class="link new-member-popup">Create new client</a>
					<div class="search-page">
						<input type="search" name="search-clients" class="in-search" />
					</div>
				</div>
				<div class="new-member-wrap">
					<div id="new-member" class="new-member-inner">
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
								<a href="javascript:;" class="btn green">Save</a>
							</div>
						</div>
					</div>
				</div>
				<div class="alpha">
					<ul>
						<li>
							<a href="javascript:;">a</a>
						</li>
						<li>
							<a href="javascript:;">b</a>
						</li>
						<li>
							<a href="javascript:;">c</a>
						</li>
						<li>
							<a href="javascript:;">d</a>
						</li>
						<li>
							<a href="javascript:;">e</a>
						</li>
						<li class="active">
							<a href="javascript:;">f</a>
						</li>
						<li>
							<a href="javascript:;">g</a>
						</li>
						<li>
							<a href="javascript:;">h</a>
						</li>
						<li>
							<a href="javascript:;">i</a>
						</li>
						<li>
							<a href="javascript:;">j</a>
						</li>
						<li>
							<a href="javascript:;">k</a>
						</li>
						<li>
							<a href="javascript:;">l</a>
						</li>
						<li class="disabled">
							<a href="javascript:;">m</a>
						</li>
						<li>
							<a href="javascript:;">n</a>
						</li>
						<li>
							<a href="javascript:;">o</a>
						</li>
						<li>
							<a href="javascript:;">p</a>
						</li>
						<li>
							<a href="javascript:;">q</a>
						</li>
						<li>
							<a href="javascript:;">r</a>
						</li>
						<li>
							<a href="javascript:;">s</a>
						</li>
						<li>
							<a href="javascript:;">t</a>
						</li>
						<li>
							<a href="javascript:;">u</a>
						</li>
						<li>
							<a href="javascript:;">v</a>
						</li>
						<li>
							<a href="javascript:;">w</a>
						</li>
						<li>
							<a href="javascript:;">x</a>
						</li>
						<li>
							<a href="javascript:;">y</a>
						</li>
						<li class="last">
							<a href="javascript:;">z</a>
						</li>					
					</ul>
				</div>
				<div class="accordion-wrap clients">
                <h3>Clients</h3>
      
          {clients.map((client) => (
            <tr key={client.id}>
                
              <input value={client.clientName}></input>
              <input value={client.name}></input>
              <input value={client.name}></input>
              <input value={client.name}></input>

              <td>
              {/*<button className="btn btn-success" onClick={ () => skiniPDGradjanina(gradjanin.korisnickoIme)}>Preuzmi PDF Zaposlenja</button>*/}
              </td>
            </tr> 
            
          ))}
      
				</div>
				{/*<div class="pagination">
					<ul>
						<li>
							<a href="#" onClick={ () => setPageNumber(1)}>1</a>
						</li>
						<li>
							<a href="#" onClick={ () => setPageNumber(2)}  >2</a>
						</li>
						<li>
							<a href="javascript:;">3</a>
						</li>
						<li class="last">
							<a onClick={ () => setPageNumber(pageNumber+1)}>Next</a>
						</li>
					</ul>
          </div>*/}
			</section>			
		</div>
        </div>
    )
}
