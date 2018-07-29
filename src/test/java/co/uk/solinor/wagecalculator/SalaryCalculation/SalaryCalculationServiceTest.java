package co.uk.solinor.wagecalculator.SalaryCalculation;

import co.uk.solinor.wagecalculator.Csv.CsvFileReader;
import co.uk.solinor.wagecalculator.Domain.Employee;
import co.uk.solinor.wagecalculator.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SalaryCalculationServiceTest {
	@Mock
	private CsvFileReader csvFileReader = Mockito.mock(CsvFileReader.class);

	private SalaryProperties salaryProperties;

	private EmployeeSalaryCalculator employeeSalaryCalculator;

	@BeforeEach
	public void setup() {
		salaryProperties = new SalaryProperties();
		salaryProperties.setStandardRate(4.25);
		salaryProperties.setExtraPayBonus(1.25);
		salaryProperties.setOvertimeRate1(25);
		salaryProperties.setOvertimeRate2(50);
		salaryProperties.setOvertimeRate3(100);
		employeeSalaryCalculator = new EmployeeSalaryCalculator(salaryProperties);
	}

	@Test
	void shouldCalcualateSalaryForNineToFive() throws IOException {
		when(csvFileReader.parseCSVToSalaryDataObject("test")).thenReturn(TestUtils.salaryDataMap("9:00", "17:00"));
		SalaryCalculationService salaryCalculationService = new SalaryCalculationService(csvFileReader, employeeSalaryCalculator);
		List<Employee> employeeList = salaryCalculationService.calculateSalariesFromCsv("test");
		assertEquals(employeeList.size(), 1);
		assertEquals(employeeList.get(0).getCalculatedSalary().getTotalSalary().doubleValue(), 34.0);
	}

	@Test
	void shouldCalcualateSalaryForNineToNine() throws IOException {
		when(csvFileReader.parseCSVToSalaryDataObject("test")).thenReturn(TestUtils.salaryDataMap("9:00", "21:00"));
		SalaryCalculationService salaryCalculationService = new SalaryCalculationService(csvFileReader, employeeSalaryCalculator);
		List<Employee> employeeList = salaryCalculationService.calculateSalariesFromCsv("test");
		assertEquals(employeeList.size(), 1);
		assertEquals(employeeList.get(0).getCalculatedSalary().getTotalSalary().doubleValue(), 57.74);
		assertEquals(employeeList.get(0).getCalculatedSalary().getStandardSalary().doubleValue(), 36.5);
		assertEquals(employeeList.get(0).getCalculatedSalary().getOvertimeSalary().doubleValue(), 21.24);
	}

	@Test
	void shouldCalcualateSalaryForNineToNineForTwoDays() throws IOException {

		when(csvFileReader.parseCSVToSalaryDataObject("test")).thenReturn(TestUtils.salaryDataMapWihtList("9:00", "21:00"));
		SalaryCalculationService salaryCalculationService = new SalaryCalculationService(csvFileReader, employeeSalaryCalculator);
		List<Employee> employeeList = salaryCalculationService.calculateSalariesFromCsv("test");
		assertEquals(employeeList.size(), 1);
		assertEquals(employeeList.get(0).getCalculatedSalary().getTotalSalary().doubleValue(), 115.48);
		assertEquals(employeeList.get(0).getCalculatedSalary().getStandardSalary().doubleValue(), 73.0);
		assertEquals(employeeList.get(0).getCalculatedSalary().getOvertimeSalary().doubleValue(), 42.48);
	}

}