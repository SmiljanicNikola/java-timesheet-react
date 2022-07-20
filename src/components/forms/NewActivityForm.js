import React from 'react'

export const NewActivityForm = () => {
	
    return (
        <div>
            <table class="default-table">
				<tr>
					<th>
						
					</th>
					<th>
						
					</th>
					<th>
						
					</th>
					<th>Description</th>
					<th class="small">
						Time <em>*</em>
					</th>
					<th class="small">Overtime</th>
				</tr>
				<tr>
					<td>
						<select>
							<option>Choose client</option>
							<option>Client 1</option>
							<option>Client 2</option>
						</select>
					</td>
					<td>
						<select>
							<option>Choose project</option>
							<option>Project 1</option>
							<option>Project 2</option>
						</select>
					</td>
					<td>
						<select>
							<option>Choose category</option>
							<option>Front-End Development</option>
							<option>Design</option>
						</select>
					</td>
					<td>
						<input type="text" class="in-text medium" />
					</td>
					<td class="small">
						<input type="text" class="in-text xsmall" />
					</td>
					<td class="small">
						<input type="text" class="in-text xsmall" />
					</td>
				</tr>
			</table>
        </div>
    )
}
