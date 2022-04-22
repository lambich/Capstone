package ca.sheridancollege.bichl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages= {"ca.sheridancollege.bichl.web","ca.sheridancollege.bichl.service"})
public class GroupingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupingSystemApplication.class, args);
	}
	
	
}