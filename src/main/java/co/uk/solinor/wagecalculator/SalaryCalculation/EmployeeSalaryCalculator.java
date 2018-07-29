package co.uk.solinor.wagecalculator.SalaryCalculation;

import co.uk.solinor.wagecalculator.Domain.CalculatedSalary;
import co.uk.solinor.wagecalculator.Domain.Employee;
import co.uk.solinor.wagecalculator.Domain.EmployeeWorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeSalaryCalculator {

	private double STANDARD_RATE;
	private double EXTRA_PAY_RATE;
	private double OVERTIME_RATE_1;
	private double OVERTIME_RATE_2;
	private double OVERTIME_RATE_3;

	@Autowired
	public EmployeeSalaryCalculator(SalaryProperties salaryProperties) {
		this.STANDARD_RATE = salaryProperties.getStandardRate();
		this.EXTRA_PAY_RATE = salaryProperties.getExtraPayBonus();
		this.OVERTIME_RATE_1 = decimalPlaceFormatter(STANDARD_RATE * (salaryProperties.getOvertimeRate1() / 100.0f) + STANDARD_RATE);
		this.OVERTIME_RATE_2 = decimalPlaceFormatter(STANDARD_RATE * (salaryProperties.getOvertimeRate2() / 100.0f) + STANDARD_RATE);
		this.OVERTIME_RATE_3 = decimalPlaceFormatter(STANDARD_RATE * (salaryProperties.getOvertimeRate3() / 100.0f) + STANDARD_RATE);
	}

	public List<Employee> calculateSalaries(List<Employee> employees) {
		List<Employee> employeeList = new ArrayList<>();
		for (Employee employee : employees) {
			employeeList.add(calculateSalaryForEmployee(employee));
		}
		return employeeList;
	}

	private Employee calculateSalaryForEmployee(Employee employee) {
		EmployeeWorkTime employeeWorkTime = employee.getEmployeeWorkTime();
		Double totalNoneOvertimeSalary = decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getStandardRateMinutes() + employeeWorkTime.getExtraPayRateMinutes()) / 60) * STANDARD_RATE);
		Double overTimeRateSalary = decimalPlaceFormatter(decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getStandardOTRate1Minutes()) / 60) * OVERTIME_RATE_1)
					                                                  + decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getStandardOTRate2Minutes()) / 60) * OVERTIME_RATE_2)
					                                                  + decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getStandardOTRate3Minutes()) / 60) * OVERTIME_RATE_3)
					                                                  + decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getExtraPayOTRate1Minutes()) / 60) * OVERTIME_RATE_1)
					                                                  + decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getExtraPayOTRate2Minutes()) / 60) * OVERTIME_RATE_2)
					                                                  + decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getExtraPayOTRate3Minutes()) / 60) * OVERTIME_RATE_3));
		Double extraTimeSalary = decimalPlaceFormatter(decimalPlaceFormatter((double) (employeeWorkTime.getExtraPayRateMinutes() + employeeWorkTime.getExtraPayOTRate1Minutes()
					                                                                               + employeeWorkTime.getExtraPayOTRate2Minutes() + employeeWorkTime.getExtraPayOTRate3Minutes()) / 60) * EXTRA_PAY_RATE);
		Double totalSalary = decimalPlaceFormatter(totalNoneOvertimeSalary + overTimeRateSalary + extraTimeSalary);
		CalculatedSalary calculatedSalary = CalculatedSalary.builder().overtimeSalary(overTimeRateSalary)
					                                    .standardSalary(totalNoneOvertimeSalary + extraTimeSalary).totalSalary(totalSalary).build();
		employee.setCalculatedSalary(calculatedSalary);
		return employee;
	}

	private static Double decimalPlaceFormatter(Double doubleToFormat) {
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		return Double.valueOf(decimalFormat.format(doubleToFormat));
	}
}
