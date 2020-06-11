package my.pro.job;


import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

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
	
	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
	          .components(new Components()
	          .addSecuritySchemes("Bearer Token", 
	          new SecurityScheme()
	          	.type(SecurityScheme.Type.HTTP)
	          	.scheme("bearer")
	          	.bearerFormat("JWT")))
	          .info(new Info()
	        		  .title("Your job API")
	        		  .description("Your job is the first free application that allows you to acheive the job of you dreams")
	        		  .version("1.0.0"));
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
