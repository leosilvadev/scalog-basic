package br.com.vaiftech.appconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@Configuration
@ComponentScan(basePackages = "br.com.vaiftech.*")
public class AppConfig {
		
	public AppConfig() {
		
	}
	
}
