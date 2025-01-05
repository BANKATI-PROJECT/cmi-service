package ma.ensa.cmi_service;

import jakarta.xml.ws.Endpoint;
import ma.ensa.cmi_service.services.CMIServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CmiServiceApplication {

	public static void main(String[] args) {
		String address = "http://localhost:8081/CMI";
		Endpoint.publish(address, new CMIServiceImpl());
		System.out.println("Service concat is running at " + address);
		SpringApplication.run(CmiServiceApplication.class, args);
	}

}
