package ma.ensa.cmi_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(scanBasePackages = "ma.ensa.cmi_service")
public class CmiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmiServiceApplication.class, args);
	}

}

