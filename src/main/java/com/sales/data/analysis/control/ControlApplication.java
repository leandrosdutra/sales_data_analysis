package com.sales.data.analysis.control;

import com.sales.data.analysis.control.files.MonitorFile;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.io.IOException;

@SpringBootApplication
@AllArgsConstructor
public class ControlApplication {

	private final MonitorFile monitorFile;

	public static void main(String[] args) {
		SpringApplication.run(ControlApplication.class, args);
	}

	@Bean
	public void run() throws IOException, InterruptedException{
		monitorFile.processExistingFiles();
		monitorFile.monitorFiles();
	}

}
