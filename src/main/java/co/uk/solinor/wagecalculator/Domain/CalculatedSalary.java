package co.uk.solinor.wagecalculator.Domain;

import lombok.Data;
import lombok.experimental.Builder;

@Builder
@Data
public class CalculatedSalary {
	private Double totalSalary;
	private Double overtimeSalary;
	private Double standardSalary;

	@Override
	public String toString() {
		return "CalculatedSalary{" +
					       "totalSalary=" + totalSalary +
					       ", overtimeSalary=" + overtimeSalary +
					       ", standardSalary=" + standardSalary +
					       '}';
	}
}
