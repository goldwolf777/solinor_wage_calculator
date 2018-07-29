package co.uk.solinor.wagecalculator.SalaryCalculation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeWorkedUtils {
	public Integer standardRateMinutes = 0;
	public Integer standardOTRate1Minutes = 0;
	public Integer standardOTRate2Minutes = 0;
	public Integer standardOTRate3Minutes = 0;
	public Integer extraPayRateMinutes = 0;
	public Integer extraPayOTRate1Minutes = 0;
	public Integer extraPayOTRate2Minutes = 0;
	public Integer extraPayOTRate3Minutes = 0;

	private Integer maxStandardAllocation = 480;
	private Integer maxOTRate1Allocation = 180;
	private Integer maxOTRate1AllocationEvening = 180;
	private Integer maxOTRate2Allocation = 60;
	private Integer maxOTRate2AllocationEvening = 60;

	public void calculateMorningMinutes(LocalDateTime endTime, LocalDateTime startTime) {
		Integer morningTime;
		if (endTime.isBefore(startTime.withHour(6))) {
			morningTime = (int) startTime.until(endTime, ChronoUnit.MINUTES);
		} else {
			morningTime = (int) startTime.until(startTime.withHour(6), ChronoUnit.MINUTES);
		}

		morningTime = morningTime < 0 ? 0 : morningTime;

		maxStandardAllocation = maxStandardAllocation - morningTime;
		extraPayRateMinutes = extraPayRateMinutes + morningTime;
	}

	public void calculateStandardMinutes(LocalDateTime startTime, LocalDateTime endTime) {
		Integer standardTime;

		if (startTime.isAfter(startTime.withHour(6))) {
			if (endTime.isBefore(startTime.withHour(19))) {
				standardTime = (int) startTime.until(endTime, ChronoUnit.MINUTES);
			} else {
				standardTime = (int) startTime.until(startTime.withHour(19), ChronoUnit.MINUTES);
			}
		} else {
			if (endTime.isBefore(startTime.withHour(19))) {
				standardTime = (int) startTime.withHour(6).until(endTime, ChronoUnit.MINUTES);
			} else {
				standardTime = (int) startTime.withHour(6).until(startTime.withHour(19), ChronoUnit.MINUTES);
			}
		}

		standardTime = standardTime < 0 ? 0 : standardTime;

		allocateStandardTime(standardTime);
	}

	private void allocateStandardTime(Integer standardTime) {
		if (standardTime > maxStandardAllocation) {
			standardRateMinutes = standardRateMinutes + maxStandardAllocation;
			standardTime = standardTime - maxStandardAllocation;
			maxStandardAllocation = 0;
		} else {
			standardRateMinutes = standardRateMinutes + standardTime;
			maxStandardAllocation = maxStandardAllocation - standardTime;
			standardTime = 0;
		}

		if (standardTime > 0) {
			if (standardTime > maxOTRate1Allocation) {
				standardOTRate1Minutes = standardOTRate1Minutes + maxOTRate1Allocation;
				standardTime = standardTime - maxOTRate1Allocation;
				maxOTRate1Allocation = 0;
			} else {
				standardOTRate1Minutes = standardOTRate1Minutes + standardTime;
				maxOTRate1Allocation = maxOTRate1Allocation - standardTime;
				standardTime = 0;
			}
		}

		if (standardTime > 0) {
			if (standardTime > maxOTRate2Allocation) {
				standardOTRate2Minutes = standardOTRate2Minutes + maxOTRate2Allocation;
				standardTime = standardTime - maxOTRate2Allocation;
				maxOTRate2Allocation = 0;
			} else {
				standardOTRate2Minutes = standardOTRate2Minutes + standardTime;
				maxOTRate2Allocation = maxOTRate2Allocation - standardTime;
				standardTime = 0;
			}
		}

		if (standardTime > 0) {
			standardOTRate3Minutes = standardOTRate3Minutes + standardTime;
		}
	}

	public void calculateEveningMinutes(LocalDateTime startTime, LocalDateTime endTime) {
		Integer eveningTime;

		if (startTime.isAfter(startTime.withHour(19))) {
			if (endTime.isAfter(endTime.withHour(6)) && endTime.getDayOfMonth() != startTime.getDayOfMonth()) {
				eveningTime = (int) startTime.until(endTime.withHour(6), ChronoUnit.MINUTES);
			} else {
				eveningTime = (int) startTime.until(endTime, ChronoUnit.MINUTES);
			}

		} else {
			if (endTime.isAfter(endTime.withHour(6)) && endTime.getDayOfMonth() != startTime.getDayOfMonth()) {
				eveningTime = (int) startTime.withHour(19).until(endTime.withHour(6), ChronoUnit.MINUTES);
			} else {
				eveningTime = (int) startTime.withHour(19).until(endTime, ChronoUnit.MINUTES);
			}
		}

		eveningTime = eveningTime < 0 ? 0 : eveningTime;

		allocateEveningTime(eveningTime);
	}

	private void allocateEveningTime(Integer eveningTime) {
		if (eveningTime > maxStandardAllocation) {
			extraPayRateMinutes = extraPayRateMinutes + maxStandardAllocation;
			eveningTime = eveningTime - maxStandardAllocation;
			maxStandardAllocation = 0;
		} else {
			extraPayRateMinutes = extraPayRateMinutes + eveningTime;
			maxStandardAllocation = maxStandardAllocation - eveningTime;
			eveningTime = 0;
		}

		if (eveningTime > 0) {
			if (eveningTime > maxOTRate1AllocationEvening) {
				extraPayOTRate1Minutes = extraPayOTRate1Minutes + maxOTRate1AllocationEvening;
				eveningTime = eveningTime - maxOTRate1AllocationEvening;
				maxOTRate1AllocationEvening = 0;
			} else {
				extraPayOTRate1Minutes = extraPayOTRate1Minutes + eveningTime;
				maxOTRate1AllocationEvening = maxOTRate1AllocationEvening - eveningTime;
				eveningTime = 0;
			}
		}

		if (eveningTime > 0) {
			if (eveningTime > maxOTRate2AllocationEvening) {
				extraPayOTRate2Minutes = extraPayOTRate2Minutes + maxOTRate2AllocationEvening;
				eveningTime = eveningTime - maxOTRate2AllocationEvening;
				maxOTRate2Allocation = 0;
			} else {
				extraPayOTRate2Minutes = extraPayOTRate2Minutes + eveningTime;
				maxOTRate2AllocationEvening = maxOTRate2AllocationEvening - eveningTime;
				eveningTime = 0;
			}
		}

		if (eveningTime > 0) {
			extraPayOTRate3Minutes = extraPayOTRate3Minutes + eveningTime;
		}
	}
}
