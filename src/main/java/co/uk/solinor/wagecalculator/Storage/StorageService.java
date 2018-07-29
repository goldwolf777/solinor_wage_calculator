package co.uk.solinor.wagecalculator.Storage;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;

public interface StorageService {

	void init();

	void store(MultipartFile file);

	List<String> loadAll();

	Path load(String filename);

	void deleteAll();

	boolean deleteByFileName(String filename);

}
