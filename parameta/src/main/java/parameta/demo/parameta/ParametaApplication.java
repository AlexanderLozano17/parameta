package parameta.demo.parameta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "parameta.demo.parameta.repository")
public class ParametaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParametaApplication.class, args);
	}

}
