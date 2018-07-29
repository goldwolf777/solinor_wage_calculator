package co.uk.solinor.wagecalculator.Launcher;

import co.uk.solinor.wagecalculator.SalaryCalculation.SalaryProperties;
import co.uk.solinor.wagecalculator.Storage.StorageProperties;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import static org.slf4j.LoggerFactory.getLogger;


@EnableAutoConfiguration
@Configuration
@ComponentScan("co.uk.solinor.wagecalculator*")
@EnableConfigurationProperties({StorageProperties.class, SalaryProperties.class})
@PropertySource(value = {"classpath:webserver.properties", "file:/etc/solinor/webserver.properties"}, ignoreResourceNotFound = true)
public class WebLauncher {

	public static void main(String[] args) {
		final Logger logger = getLogger(WebLauncher.class);
		logger.info("Starting server in Web Launcher");
		SpringApplication.run(WebLauncher.class, args);
	}
}
