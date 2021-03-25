package com.example.GcpRestApis;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sqladmin.SQLAdmin;
import com.google.api.services.sqladmin.model.DatabaseInstance;
import com.google.api.services.sqladmin.model.InstancesListResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Component
public class SqlInstanceList {
    public void sqlInstanceList(String projectId) throws IOException, GeneralSecurityException {

        SQLAdmin sqlAdminService = createSqlAdminService();
        SQLAdmin.Instances.List request = sqlAdminService.instances().list(projectId);

        InstancesListResponse response;
        do {
            response = request.execute();
            if (response.getItems() == null) {
                continue;
            }
            for (DatabaseInstance databaseInstance : response.getItems()) {
                // TODO: Change code below to process each `databaseInstance` resource:
                System.out.println(databaseInstance);
            }
            request.setPageToken(response.getNextPageToken());
        } while (response.getNextPageToken() != null);
    }

    public static SQLAdmin createSqlAdminService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleCredential credential = GoogleCredential.getApplicationDefault();
        if (credential.createScopedRequired()) {
            credential =
                    credential.createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
        }

        return new SQLAdmin.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google-SQLAdminSample/0.1")
                .build();
    }
}