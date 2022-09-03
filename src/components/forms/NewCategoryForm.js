/* eslint-disable jsx-a11y/anchor-is-valid */
import React,{useState} from 'react'
import CategoryService from '../../services/CategoryService';

export const NewCategoryForm = (props) => {
	
    const [category, setCategory] = useState({})

    function saveCategory(category){
		let newCategory = {
			type: category.type,
		}
		CategoryService.createCategory(newCategory);
    }

    return (
		(props.display == true)? (
        <div>
            <div class="popup">
				<div class="popup-inner">
					<h2>Create new Category</h2>
					<ul class="form">
						<li>
							<label>Type:</label>
							<input  name="type" id="type" value={category.type} onChange={e => setCategory({...category, type:e.target.value})} type="text" class="in-text" />
						</li>
					</ul>
					<button className="close-btn" >
						Close
					</button>
					
					<div class="buttons" style={{marginTop:'20px'}}>
						<a class="btn green" onClick={() => saveCategory(category)}>Add Category</a>
					</div>
				</div>
			</div>
		</div>
		) 
		:
		<div></div>
    )
}
