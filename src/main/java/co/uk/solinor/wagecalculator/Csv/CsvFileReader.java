package co.uk.solinor.wagecalculator.Csv;

import co.uk.solinor.wagecalculator.Domain.SalaryData;
import co.uk.solinor.wagecalculator.Storage.StorageProperties;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;


@Component
public class CsvFileReader {
	private static Logger LOG = getLogger(CsvFileReader.class);

	@Autowired
	StorageProperties storageProperties;

	public Map<Integer, List<SalaryData>> parseCSVToSalaryDataObject(String filename) throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get(storageProperties.getLocation() + "/" + filename));
		CsvToBean<SalaryData> csvToBean = new CsvToBeanBuilder(reader)
					                                  .withType(SalaryData.class)
					                                  .withIgnoreLeadingWhiteSpace(true)
					                                  .build();

		Iterator<SalaryData> csvSalaryDataIterator = csvToBean.iterator();
		HashMap<Integer, List<SalaryData>> salaryDataHashMap = new HashMap<>();
		while (csvSalaryDataIterator.hasNext()) {
			SalaryData salaryData = csvSalaryDataIterator.next();
			LOG.debug("Line read {}", salaryData);
			if (salaryDataHashMap.containsKey(salaryData.getId())) {
				List<SalaryData> salaryDataList = new ArrayList<>(salaryDataHashMap.get(salaryData.getId()));
				salaryDataList.add(salaryData);
				salaryDataHashMap.replace(salaryData.getId(), salaryDataList);
			} else {
				salaryDataHashMap.put(salaryData.getId(), Collections.singletonList(salaryData));
			}
		}
		LOG.debug("Fully read file {}", salaryDataHashMap);
		return salaryDataHashMap;
	}
}
