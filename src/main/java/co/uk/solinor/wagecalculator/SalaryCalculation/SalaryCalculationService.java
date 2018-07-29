package co.uk.solinor.wagecalculator.SalaryCalculation;

import co.uk.solinor.wagecalculator.Csv.CsvFileReader;
import co.uk.solinor.wagecalculator.Domain.Employee;
import co.uk.solinor.wagecalculator.Domain.SalaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SalaryCalculationService {
	CsvFileReader csvFileReader;
	EmployeeSalaryCalculator employeeSalaryCalculator;

	@Autowired
	public SalaryCalculationService(CsvFileReader csvFileReader, EmployeeSalaryCalculator employeeSalaryCalculator) {
		this.csvFileReader = csvFileReader;
		this.employeeSalaryCalculator = employeeSalaryCalculator;
	}

	public List<Employee> calculateSalariesFromCsv(String filename) throws IOException {
		Map<Integer, List<SalaryData>> salaryDataCsvObject = csvFileReader.parseCSVToSalaryDataObject(filename);
		EmployeeMinuteCalculator employeeMinuteCalculator = new EmployeeMinuteCalculator();
		employeeMinuteCalculator.filterSalaryDataById(salaryDataCsvObject);
		List<Employee> employeeTimes = employeeMinuteCalculator.employeeList;
		return employeeSalaryCalculator.calculateSalaries(employeeTimes);
	}
}
