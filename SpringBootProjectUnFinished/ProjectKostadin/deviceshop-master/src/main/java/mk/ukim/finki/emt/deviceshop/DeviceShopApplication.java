package mk.ukim.finki.emt.deviceshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class DeviceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeviceShopApplication.class, args);
	}

}
