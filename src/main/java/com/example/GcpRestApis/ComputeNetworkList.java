package com.example.GcpRestApis;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.compute.Compute;
import com.google.api.services.compute.model.Network;
import com.google.api.services.compute.model.NetworkList;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Component
public class ComputeNetworkList {
    public void computeNetworkList(String project) throws IOException, GeneralSecurityException {

        Compute computeService = createComputeService();
        Compute.Networks.List request = computeService.networks().list(project);

        NetworkList response;
        do {
            response = request.execute();
            if (response.getItems() == null) {
                continue;
            }
            for (Network network : response.getItems()) {
                // TODO: Change code below to process each `network` resource:
                System.out.println(network);
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