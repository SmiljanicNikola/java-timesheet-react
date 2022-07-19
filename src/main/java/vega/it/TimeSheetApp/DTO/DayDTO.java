package vega.it.TimeSheetApp.DTO;

import java.time.LocalDate;

public class DayDTO {

	private Integer time;
	
	private Integer overtime;
	
	private LocalDate date;

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

	public DayDTO(Integer time, Integer overtime, LocalDate date) {
		super();
		this.time = time;
		this.overtime = overtime;
		this.date = date;
	}

	@Override
	public String toString() {
		return "DayDTO [time=" + time + ", overtime=" + overtime + ", date=" + date + "]";
	}
	
	
	
}
