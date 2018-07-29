package co.uk.solinor.wagecalculator.Domain;

import com.opencsv.bean.CsvBindByName;

public class SalaryData {

	@CsvBindByName(column = "Person Name")
	private String name;

	@CsvBindByName(column = "Person Id")
	private Integer id;

	@CsvBindByName(column = "Date")
	private String date;

	@CsvBindByName(column = "Start")
	private String startTime;

	@CsvBindByName(column = "End")
	private String endTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "SalaryData{" +
					       "name='" + name + '\'' +
					       ", id='" + id + '\'' +
					       ", date='" + date + '\'' +
					       ", startTime='" + startTime + '\'' +
					       ", endTime='" + endTime + '\'' +
					       '}';
	}
}
