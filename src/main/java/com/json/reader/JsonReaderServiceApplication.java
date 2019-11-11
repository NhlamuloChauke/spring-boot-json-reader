package com.json.reader;

import com.json.reader.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(scanBasePackages ="com.json.reader.service")
@EnableConfigurationProperties(AppProperties.class)
public class JsonReaderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonReaderServiceApplication.class, args);
	}

}
