package com.example.GcpRestApis;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.compute.Compute;
import com.google.api.services.compute.model.Subnetwork;
import com.google.api.services.compute.model.SubnetworkList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Component
public class ComputeNetworkSubnetLists {
    public void computeNetworkSubnetLists(String project, String region) throws IOException, GeneralSecurityException {

        Compute computeService = createComputeService();
        Compute.Subnetworks.List request = computeService.subnetworks().list(project, region);

        SubnetworkList response;
        do {
            response = request.execute();
            if (response.getItems() == null) {
                continue;
            }
            for (Subnetwork subnetwork : response.getItems()) {
                // TODO: Change code below to process each `subnetwork` resource:
                System.out.println(subnetwork);
            }
            request.setPageToken(response.getNextPageToken());
        } while (response.getNextPageToken() != null);
    }

    public static Compute createComputeService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredential credential = GoogleCredential.getApplicationDefault();
        if (credential.createScopedRequired()) {
            credential =
                    credential.createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
        }

        return new Compute.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google-ComputeSample/0.1")
                .build();
    }
}