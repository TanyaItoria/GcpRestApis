package com.example.GcpRestApis;
import com.google.api.gax.paging.Page;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQuery.TableListOption;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.DatasetId;
import com.google.cloud.bigquery.Table;
import org.springframework.stereotype.Component;

@Component
public class BigQueryList {
    public void listTables(String projectId, String datasetName) {
        try {
            // Initialize client that will be used to send requests. This client only needs to be created
            // once, and can be reused for multiple requests.
            BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();

            DatasetId datasetId = DatasetId.of(projectId, datasetName);
            Page<Table> tables = bigquery.listTables(datasetId, TableListOption.pageSize(100));
            tables.iterateAll().forEach(table -> System.out.print(table.getTableId().getTable() + "\n"));

            System.out.println("Tables listed successfully.");
        } catch (BigQueryException e) {
            System.out.println("Tables were not listed. Error occurred: " + e.toString());
        }
    }
}