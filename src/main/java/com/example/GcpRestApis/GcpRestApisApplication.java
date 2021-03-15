package com.example.GcpRestApis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;

@SpringBootApplication
public class GcpRestApisApplication implements CommandLineRunner {



	public static void main(String[] args) {
		SpringApplication.run(GcpRestApisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Creating a HttpClient object
		CloseableHttpClient httpclient = HttpClients.createDefault();
		//Creating a HttpGet object
		HttpGet httpget = new HttpGet("https://www.tutorialspoint.com/ ");
		//Printing the method used
		System.out.println("Request Type: "+httpget.getMethod());
		//Executing the Get request
		HttpResponse httpresponse = httpclient.execute(httpget);
		Scanner sc = new Scanner(httpresponse.getEntity().getContent());
		//Printing the status line
		System.out.println(httpresponse.getStatusLine());
		while(sc.hasNext()) {
			System.out.println(sc.nextLine());
		}
	}




}
