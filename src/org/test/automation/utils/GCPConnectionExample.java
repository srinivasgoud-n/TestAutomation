package org.test.automation.utils;

import com.google.cloud.storage.Storage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.StorageOptions;

public class GCPConnectionExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
            // Load the Google Cloud Platform credentials
            GoogleCredentials credentials = GoogleCredentials.getApplicationDefault();

            // Set the project ID
//            String projectId = "sys-68761289465306235591840817";
            
            String projectId = "basic-zenith-389713 ";

            // Create a storage client with the project ID and credentials
            Storage storage = StorageOptions.newBuilder()
                    .setProjectId(projectId)
                    .setCredentials(credentials)
                    .build()
                    .getService();

            // Use the storage client to perform operations on Google Cloud Storage
            // For example, you can list buckets:
            storage.list().iterateAll().forEach(bucket -> System.out.println(bucket.getName()));
            
            // Or you can perform other operations based on your requirements

        } catch (Exception e) {
            // Handle any exceptions that occur during the process
            e.printStackTrace();
        }
    }

	}

