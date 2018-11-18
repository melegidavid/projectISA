package ftn.isa.projectISA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan(basePackages = {"ftn.isa.model"})
public class ProjectIsaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectIsaApplication.class, args);
	}
}
