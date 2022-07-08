import React, {useEffect, useState} from 'react'
import './style.css'
import {useNavigate} from 'react-router-dom'

export const NewProjectForm = (props) => {
    const [takenProps, setTakenProps] = useState(props.display);
    const [display, setDisplay] = useState(false);
    const navigate = useNavigate();
    
    useState(() =>{
        setTakenProps(props.display)
    })

	function closePopup(){
		props.display = false;
    }
    
    function close(){
        
    }


    return (
        (props.display == true)?(
            
        <div>
            <div class="popup">
					<div id="new-project" class="popup-inner">
						<h2>Create new project</h2>
						<ul class="form">
							<li>
								<label>Project name:</label>
								<input type="text" class="in-text" />
							</li>								
							<li>
								<label>Description:</label>
								<input type="text" class="in-text" />
							</li>
							<li>
								<label>Customer:</label>
								<select>
									<option>Select customer</option>
									<option>Adam Software NV</option>
									<option>Clockwork</option>
									<option>Emperor Design</option>
								</select>
							</li>
							<li>
								<label>Lead:</label>
								<select>
									<option>Select lead</option>
									<option>Sasa Popovic</option>
									<option>Sladjana Miljanovic</option>
								</select>
							</li>
						</ul>
                        <button className="close-btn" onClick={ () => closePopup()}>
							Close
						</button>
						<div class="buttons">
							<div class="inner">
								<a href="javascript:;" class="btn green">Save</a>
							</div>
						</div>
					</div>
				</div>
        </div>):<div></div>
    )
}
