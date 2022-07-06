package vega.it.TimeSheetApp.DTO;


import vega.it.TimeSheetApp.model.Category;

public class CategoryDTO {

	private Integer id;
	
	private String type;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category category) {
		this(category.getId(), category.getType());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CategoryDTO(Integer id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public CategoryDTO(String type) {
		super();
		this.type = type;
	}
	
	
	
	
	
}
