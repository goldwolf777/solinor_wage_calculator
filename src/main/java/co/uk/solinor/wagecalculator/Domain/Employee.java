package co.uk.solinor.wagecalculator.Domain;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class Employee {
	private String employeeName;
	private Integer employeeId;
	private EmployeeWorkTime employeeWorkTime;
	private CalculatedSalary calculatedSalary;

	@Override
	public String toString() {
		return "Employee{" +
					       "employeeName='" + employeeName + '\'' +
					       ", employeeId=" + employeeId +
					       ", employeeWorkTime=" + employeeWorkTime +
					       ", calculatedSalary=" + calculatedSalary +
					       '}';
	}
}
