package co.uk.solinor.wagecalculator.Domain;

import lombok.Data;
import lombok.experimental.Builder;

@Data
@Builder
public class EmployeeWorkTime {
	private Integer standardRateMinutes;
	private Integer standardOTRate1Minutes;
	private Integer standardOTRate2Minutes;
	private Integer standardOTRate3Minutes;
	private Integer extraPayRateMinutes;
	private Integer extraPayOTRate1Minutes;
	private Integer extraPayOTRate2Minutes;
	private Integer extraPayOTRate3Minutes;

	@Override
	public String toString() {
		return "EmployeeWorkTime{" +
					       "standardRateMinutes=" + standardRateMinutes +
					       ", standardOTRate1Minutes=" + standardOTRate1Minutes +
					       ", standardOTRate2Minutes=" + standardOTRate2Minutes +
					       ", standardOTRate3Minutes=" + standardOTRate3Minutes +
					       ", extraPayRateMinutes=" + extraPayRateMinutes +
					       ", extraPayOTRate1Minutes=" + extraPayOTRate1Minutes +
					       ", extraPayOTRate2Minutes=" + extraPayOTRate2Minutes +
					       ", extraPayOTRate3Minutes=" + extraPayOTRate3Minutes +
					       '}';
	}
}
