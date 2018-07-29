package co.uk.solinor.wagecalculator.Storage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class FileSystemStorageService implements StorageService {
	private static Logger LOG = getLogger(FileSystemStorageService.class);


	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
							"Cannot store file with relative path outside current directory "
										+ filename);
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, this.rootLocation.resolve(filename),
							StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	@Override
	public List<String> loadAll() {
		try {
			File file = this.rootLocation.toFile();
			if (!file.exists()) {
				file.mkdirs();
			}
			return Files.walk(this.rootLocation, 1)
						       .filter(path -> !path.equals(this.rootLocation))
						       .map(c -> c.getFileName().toString())
						       .collect(Collectors.toList());
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}


	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public boolean deleteByFileName(String filename) {
		File fileToRemove = rootLocation.resolve(filename).toFile();
		if (fileToRemove.exists() && fileToRemove.delete()) {
			LOG.debug("File {} remove", filename);
			return true;
		}
		LOG.debug("Unable to delete the file. File {} doesn't exist", filename);
		return false;
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize Storage", e);
		}
	}
}
