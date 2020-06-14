package my.pro.job;


import java.nio.charset.StandardCharsets;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * 
 * @author Elkotb Zakaria
 *
 */
@EnableWebSecurity
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
	
	@Bean
    public SpringTemplateEngine springTemplateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        return templateEngine;
    }
    @Bean
    public SpringResourceTemplateResolver htmlTemplateResolver(){
        SpringResourceTemplateResolver emailTemplateResolver = new SpringResourceTemplateResolver();
        emailTemplateResolver.setPrefix("classpath:templates/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode(TemplateMode.HTML);
        emailTemplateResolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return emailTemplateResolver;
    }

	@Override
	public void run(String... args) throws Exception {
		
	}

}
