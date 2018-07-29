package co.uk.solinor.wagecalculator;

import co.uk.solinor.wagecalculator.Domain.SalaryData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestUtils {

	public static Map<Integer, List<SalaryData>> salaryDataMap(String startTime, String endTime) {
		SalaryData salaryData = salaryData(startTime, endTime, "4.3.2015", 1, "Test");
		Map<Integer, List<SalaryData>> salaryDataMap = new HashMap<>();
		salaryDataMap.put(1, Collections.singletonList(salaryData));
		return salaryDataMap;
	}

	public static Map<Integer, List<SalaryData>> salaryDataMapWihtList(String startTime, String endTime) {
		List<SalaryData> salaryDataList = new ArrayList<>();
		salaryDataList.add(salaryData(startTime, endTime, "4.3.2015", 1, "Test"));
		salaryDataList.add(salaryData(startTime, endTime, "5.3.2015", 1, "Test"));
		Map<Integer, List<SalaryData>> salaryDataMap = new HashMap<>();
		salaryDataMap.put(1, salaryDataList);
		return salaryDataMap;
	}

	public static SalaryData salaryData(String startTime, String endTime, String date, Integer id, String name) {
		SalaryData salaryData = new SalaryData();
		salaryData.setName(name);
		salaryData.setId(id);
		salaryData.setDate(date);
		salaryData.setEndTime(endTime);
		salaryData.setStartTime(startTime);
		return salaryData;
	}
}
