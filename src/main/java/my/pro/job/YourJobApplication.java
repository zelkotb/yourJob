package my.pro.job;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author Elkotb Zakaria
 *
 */
@EnableAsync
@SpringBootApplication
public class YourJobApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(YourJobApplication.class, args);
	}
	
	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean
	BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
