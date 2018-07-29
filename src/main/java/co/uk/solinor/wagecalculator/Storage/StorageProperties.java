package co.uk.solinor.wagecalculator.Storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@ConfigurationProperties
@PropertySource(value = {"classpath:webserver.properties", "file:/etc/solinor/webserver.properties"}, ignoreResourceNotFound = true)
public class StorageProperties {

	/**
	 * Folder location for storing files
	 */
	@Value("${file.storage.location:/etc/solinor/csv}")
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
