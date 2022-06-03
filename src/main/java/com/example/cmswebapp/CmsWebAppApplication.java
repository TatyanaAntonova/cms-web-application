package com.example.cmswebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CmsWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsWebAppApplication.class, args);

		System.out.println("Application starts working!");
	}

}
