package com.hospital.review;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootJpaHospitalReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaHospitalReviewApplication.class, args);
	}

}
