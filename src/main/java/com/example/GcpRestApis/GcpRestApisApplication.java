package com.example.GcpRestApis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GcpRestApisApplication implements CommandLineRunner {

	@Autowired private VmInstanceFetcher vmInstanceFetcher;
	@Autowired private ListBuckets listBuckets;
	@Autowired private ComputeDiskList computeDiskList;
	@Autowired private ComputeNetworkList computeNetworkList;
	@Autowired private ComputeNetworkSubnetLists computeNetworkSubnetLists;
	@Autowired private BigQueryList bigQueryList;
	@Autowired private SqlInstanceList sqlInstanceList;

	public static void main(String[] args) {
		SpringApplication.run(GcpRestApisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("");
		System.out.println("**************************************   VM instance List   **************************************");
		System.out.println("");
		vmInstanceFetcher.listVmInstances("durable-trainer-251010", "us-central1-c" );
		System.out.println("");
		System.out.println("**************************************   Bucket List   **************************************");
		System.out.println("");
		listBuckets.listBuckets("durable-trainer-251010");
		System.out.println("");
		System.out.println("**************************************   Compute disk List   **************************************");
		System.out.println("");
		computeDiskList.computeDiskList("durable-trainer-251010", "us-central1-c");
		System.out.println("");
		System.out.println("**************************************   Compute network list   **************************************");
		System.out.println("");
		computeNetworkList.computeNetworkList("durable-trainer-251010");
		System.out.println("");
		System.out.println("**************************************   Compute network subnet list   **************************************");
		System.out.println("");
		computeNetworkSubnetLists.computeNetworkSubnetLists("durable-trainer-251010", "us-west1");
		System.out.println("");
		System.out.println("**************************************   Big Query List   **************************************");
		System.out.println("");
		bigQueryList.listTables("durable-trainer-251010","akxahdkv");
		System.out.println("");
		System.out.println("**************************************   SQL Instance List   **************************************");
		System.out.println("");
		sqlInstanceList.sqlInstanceList("durable-trainer-251010");
		System.out.println("**************************************    **************************************");
		System.out.println("");



	}

}
