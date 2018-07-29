package co.uk.solinor.wagecalculator.SalaryCalculation;

import co.uk.solinor.wagecalculator.Domain.Employee;
import co.uk.solinor.wagecalculator.TestUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeMinuteCalculatorTest {

	@Test
	void shouldCorrectlyCalculateNineToFiveMinutesWorked() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("9:00", "17:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(480, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}

	@Test
	void shouldCorrectlyCalculateStandardOvertime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("9:00", "19:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(480, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(120, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}

	@Test
	void shouldCorrectlyCalculateStandardAndExtraOvertime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("9:00", "20:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(480, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(120, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(60, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}

	@Test
	void shouldCorrectlyCalculateExtraTime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("19:00", "20:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(0, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(60, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}

	@Test
	void shouldCorrectlyCalculateStandardAndExtraTime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("18:00", "20:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(60, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(60, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}


	@Test
	void shouldCorrectlyCalculateMorningTimeAndOvertime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("4:00", "16:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(360, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(180, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(60, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(120, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}

	@Test
	void shouldCorrectlyCalculatNextDayTime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("22:00", "2:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(0, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(240, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}

	@Test
	void shouldCorrectlyCalculatNormalTimeAndNextDayTime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("18:00", "1:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(60, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(360, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}

	@Test
	void shouldCorrectlyCalculatNormalTimeAndNextDayTimeAndOvertime() {
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(TestUtils.salaryDataMap("18:00", "6:00"));
		List<Employee> employeeList = employeeMinuteCalculator.employeeList;
		Employee employee = employeeList.get(0);
		assertEquals(employeeList.size(), 1);
		assertEquals("Test", employee.getEmployeeName());
		assertEquals(employee.getEmployeeId(), new Integer(1));
		assertEquals(60, employee.getEmployeeWorkTime().getStandardRateMinutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate1Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getStandardOTRate3Minutes().intValue());
		assertEquals(420, employee.getEmployeeWorkTime().getExtraPayRateMinutes().intValue());
		assertEquals(180, employee.getEmployeeWorkTime().getExtraPayOTRate1Minutes().intValue());
		assertEquals(60, employee.getEmployeeWorkTime().getExtraPayOTRate2Minutes().intValue());
		assertEquals(0, employee.getEmployeeWorkTime().getExtraPayOTRate3Minutes().intValue());
	}


}