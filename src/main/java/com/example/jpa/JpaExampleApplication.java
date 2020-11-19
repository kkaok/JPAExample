package com.example.jpa;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.jpa.emp.entity.Employee;
import com.example.jpa.emp.repository.EmployeeRepository;

@SpringBootApplication
public class JpaExampleApplication {

	private static final Logger log = LoggerFactory.getLogger(JpaExampleApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}

	  @Bean
	  public CommandLineRunner demo(EmployeeRepository repository) {
	    return (args) -> {
	      // save a few customers
	      repository.save(new Employee("Jack", "Bauer"));
	      repository.save(new Employee("Chloe", "O'Brian"));
	      repository.save(new Employee("Kim", "Bauer"));
	      repository.save(new Employee("David", "Palmer"));
	      repository.save(new Employee("Michelle", "Dessler"));

	      // fetch all Employees
	      log.info("Employees found with findAll():");
	      log.info("-------------------------------");
	      for (Employee employee : repository.findAll()) {
	        log.info(employee.toString());
	      }
	      log.info("");

	      // fetch an individual customer by ID
	      Optional<Employee> employee = repository.findById(1L);
	      log.info("Employee found with findById(1L):");
	      log.info("--------------------------------");
	      log.info(employee.toString());
	      log.info("");

	      // fetch customers by last name
	      log.info("Employee found with findByLastName('Bauer'):");
	      log.info("--------------------------------------------");
	      repository.findByLastName("Bauer").forEach(bauer -> {
	        log.info(bauer.toString());
	      });
	      log.info("Employee found with findByFirstName('Kim'):");
	      log.info("--------------------------------------------");
	      repository.findByFirstName("Kim").forEach(bauer -> {
	    	  log.info(bauer.toString());
	      });
	      // for (Employee bauer : repository.findByLastName("Bauer")) {
	      //  log.info(bauer.toString());
	      // }
	      log.info("");
	    };
	  }
}
