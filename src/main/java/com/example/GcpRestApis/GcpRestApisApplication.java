package com.example.GcpRestApis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringBootApplication
public class GcpRestApisApplication implements CommandLineRunner {

	@Autowired private VmInstanceFetcher vmInstanceFetcher;


	public static void main(String[] args) {
		SpringApplication.run(GcpRestApisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		vmInstanceFetcher.listVmInstances("durable-trainer-251010", "us-central1-c" );
	}

}
