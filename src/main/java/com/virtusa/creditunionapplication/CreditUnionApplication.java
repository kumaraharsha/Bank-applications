package com.virtusa.creditunionapplication;

import com.virtusa.creditunionapplication.model.Person;
import com.virtusa.creditunionapplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@SpringBootApplication
public class CreditUnionApplication {

	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(CreditUnionApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean someFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(someFilter());
		registration.addUrlPatterns("/url/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("someFilter");
		registration.setOrder(1);
		return registration;
	}

	public Filter someFilter() {
		return new AuthFilter();
	}

	@Bean
	public ApplicationRunner runner(){
		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {
				personRepository.addPerson(new Person("Rachel","Green","12334345","4242",true));
				personRepository.addPerson(new Person("Monica","Geller","456678768","2343",false));
				personRepository.addPerson(new Person("Phoebe","Buffay","4645764","546456",true));
				personRepository.addPerson(new Person("Joey","Tribbiani","678687676","3242",false));
				personRepository.addPerson(new Person("Chandler","Bing","897897987","457665",true));
			}
		};
	}
}
