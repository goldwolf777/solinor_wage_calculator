package co.uk.solinor.wagecalculator.SalaryCalculation;

import co.uk.solinor.wagecalculator.Domain.Employee;
import co.uk.solinor.wagecalculator.Domain.EmployeeWorkTime;
import co.uk.solinor.wagecalculator.Domain.SalaryData;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class EmployeeMinuteCalculator {
	private static Logger LOG = getLogger(EmployeeMinuteCalculator.class);

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy.H:mm");
	TimeWorkedUtils timeWorkedUtils = null;
	public List<Employee> employeeList = new ArrayList<>();
	private Integer standardRateMinutes = 0;
	private Integer standardOTRate1Minutes = 0;
	private Integer standardOTRate2Minutes = 0;
	private Integer standardOTRate3Minutes = 0;
	private Integer extraPayRateMinutes = 0;
	private Integer extraPayOTRate1Minutes = 0;
	private Integer extraPayOTRate2Minutes = 0;
	private Integer extraPayOTRate3Minutes = 0;

	public void filterSalaryDataById(Map<Integer, List<SalaryData>> salaryDataHashMap) {
		String employeeName = "";
		Integer employeeId = null;
		for (Integer key : salaryDataHashMap.keySet()) {
			List<SalaryData> salaryDataList = salaryDataHashMap.get(key);
			LocalDateTime previousEntryDate = null;
			for (SalaryData salaryData : salaryDataList) {
				employeeName = salaryData.getName();
				employeeId = salaryData.getId();
				if (previousEntryDate != null && previousEntryDate.getDayOfMonth() == LocalDateTime.parse(salaryData.getDate() + "." + salaryData.getStartTime(), formatter).getDayOfMonth()) {
					calculateSalaryForADay(timeWorkedUtils, salaryData);
					previousEntryDate = LocalDateTime.parse(salaryData.getDate() + "." + salaryData.getStartTime(), formatter);
				} else {
					if (timeWorkedUtils != null) {
						addUpTime();
					}
					timeWorkedUtils = new TimeWorkedUtils();
					calculateSalaryForADay(timeWorkedUtils, salaryData);
					previousEntryDate = LocalDateTime.parse(salaryData.getDate() + "." + salaryData.getStartTime(), formatter);
				}
			}
			if (timeWorkedUtils != null) {
				addUpTime();
			}
			EmployeeWorkTime employeeWorkTime = EmployeeWorkTime.builder()
						                                    .extraPayOTRate1Minutes(extraPayOTRate1Minutes)
						                                    .extraPayOTRate2Minutes(extraPayOTRate2Minutes).extraPayOTRate3Minutes(extraPayOTRate3Minutes)
						                                    .standardOTRate1Minutes(standardOTRate1Minutes).standardOTRate2Minutes(standardOTRate2Minutes)
						                                    .standardOTRate3Minutes(standardOTRate3Minutes).standardRateMinutes(standardRateMinutes)
						                                    .extraPayRateMinutes(extraPayRateMinutes).build();

			Employee employee = Employee.builder().employeeId(employeeId)
						                    .employeeName(employeeName).employeeWorkTime(employeeWorkTime).build();
			employeeList.add(employee);
			resetTime();
		}
	}

	private void resetTime() {
		extraPayOTRate1Minutes = 0;
		extraPayOTRate2Minutes = 0;
		extraPayOTRate3Minutes = 0;
		extraPayRateMinutes = 0;
		standardRateMinutes = 0;
		standardOTRate1Minutes = 0;
		standardOTRate2Minutes = 0;
		standardOTRate3Minutes = 0;
	}

	private void addUpTime() {
		extraPayOTRate1Minutes += timeWorkedUtils.extraPayOTRate1Minutes;
		extraPayOTRate2Minutes += timeWorkedUtils.extraPayOTRate2Minutes;
		extraPayOTRate3Minutes += timeWorkedUtils.extraPayOTRate3Minutes;
		extraPayRateMinutes += timeWorkedUtils.extraPayRateMinutes;
		standardRateMinutes += timeWorkedUtils.standardRateMinutes;
		standardOTRate1Minutes += timeWorkedUtils.standardOTRate1Minutes;
		standardOTRate2Minutes += timeWorkedUtils.standardOTRate2Minutes;
		standardOTRate3Minutes += timeWorkedUtils.standardOTRate3Minutes;
	}

	public void calculateSalaryForADay(TimeWorkedUtils timeWorkedUtils, SalaryData salaryData) {

		LocalDateTime startDate = LocalDateTime.parse(salaryData.getDate() + "." + salaryData.getStartTime(), formatter);
		LocalDateTime endDate = LocalDateTime.parse(salaryData.getDate() + "." + salaryData.getEndTime(), formatter);
		if (startDate.until(endDate, ChronoUnit.MINUTES) < 0L) {
			endDate = endDate.plusDays(1L);
		}
		timeWorkedUtils.calculateMorningMinutes(endDate, startDate);
		timeWorkedUtils.calculateStandardMinutes(startDate, endDate);
		timeWorkedUtils.calculateEveningMinutes(startDate, endDate);
	}


}
