package com.governance.eGovernance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = { "com.governance.service", "com.governance.controller",
		"com.governance.exception" })
@EnableJpaRepositories("com.governance.repository")
@EntityScan("com.governance.entity")
public class EGovernanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EGovernanceApplication.class, args);
		System.out.println("!!!Welcome To E_governance System!!!");
		
	}
}
