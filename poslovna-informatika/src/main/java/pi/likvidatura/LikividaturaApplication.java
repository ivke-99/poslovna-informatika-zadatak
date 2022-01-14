package pi.likvidatura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaRepositories
@Configuration
@ComponentScan
public class LikividaturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikividaturaApplication.class, args);
	}

}
