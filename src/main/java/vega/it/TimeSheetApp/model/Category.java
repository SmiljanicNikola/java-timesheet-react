package vega.it.TimeSheetApp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="category_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name="type", unique=false, nullable=false)
	private String type;
	
	@OneToMany(mappedBy="category")
	private List<TimeSheetActivity> timeSheetActivities;

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

	public List<TimeSheetActivity> getTimeSheetActivities() {
		return timeSheetActivities;
	}

	public void setTimeSheetActivities(List<TimeSheetActivity> timeSheetActivities) {
		this.timeSheetActivities = timeSheetActivities;
	}

	public Category(Integer id, String type, List<TimeSheetActivity> timeSheetActivities) {
		super();
		this.id = id;
		this.type = type;
		this.timeSheetActivities = timeSheetActivities;
	}

	public Category(String type) {
		super();
		this.type = type;
	}

	public Category() {
		super();
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", timeSheetActivities=" + timeSheetActivities + "]";
	}
	
	
}
