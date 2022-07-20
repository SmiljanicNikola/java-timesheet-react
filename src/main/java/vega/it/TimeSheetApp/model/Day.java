package vega.it.TimeSheetApp.model;

import java.time.LocalDate;

public class Day {

	private Integer id;
	
	private Integer time;
	
	private Integer overtime;
	
	private LocalDate date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getOvertime() {
		return overtime;
	}

	public void setOvertime(Integer overtime) {
		this.overtime = overtime;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Day(Integer id, Integer time, Integer overtime, LocalDate date) {
		super();
		this.id = id;
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	public Day(Integer time, Integer overtime, LocalDate date) {
		super();
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	public Day() {
		super();
	}
	
	

	
}
