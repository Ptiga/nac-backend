package com.socgen.nac;

//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NacApplication {


	public static void main(String[] args) {
		SpringApplication.run(NacApplication.class, args);
	}
/*
	@Bean
	public Hibernate5Module datatypeHibernateModule(){
		return new Hibernate5Module();
	}
*/
}
