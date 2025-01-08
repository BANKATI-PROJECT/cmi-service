package ma.ensa.cmi_service;

import jakarta.xml.ws.Endpoint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
public class CmiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmiServiceApplication.class, args);
	}

}

