package cl.Ferramas.Ferramas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity 
public class FerramasApplication {

	public static void main(String[] args) {
		SpringApplication.run(FerramasApplication.class, args);
	}

}

