package ftn.isa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
public class ProjectIsaApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ProjectIsaApplication.class, args);
	}
}

