package com.mycompany.studentmanagementapp.service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class BlobStorageService {

    @Value("${azure.storage.container-name}")
    private String containerName;

    private String containerName1="pdf";
    @Autowired
    private BlobServiceClient blobServiceClient;
    private static final String DOCUMENT_BASE_LOCATION = "https://svvvplacementdata.blob.core.windows.net/images/";


    public String uploadFile(MultipartFile file) {
        try {
            // Generate a unique blob name to avoid conflicts
            String blobName = UUID.randomUUID().toString() + "/" + file.getOriginalFilename();
            BlobClient blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobName);
            ;
            // Get a reference to the blob client
            if (file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png")) {
                blobClient = blobServiceClient.getBlobContainerClient(containerName).getBlobClient(blobName);
            }
            if (file.getContentType().equals("application/pdf")) {
                 blobClient = blobServiceClient.getBlobContainerClient(containerName1).getBlobClient(blobName);
            }
            // Open an input stream from the uploaded file
            try (InputStream inputStream = file.getInputStream()) {
                // Upload the file to Azure Blob Storage
                blobClient.upload(inputStream, file.getSize(), true);

                // Return the URL of the uploaded blob
                return blobName;
            }
        } catch (IOException e) {
            // Handle IOException (e.g., log the error)
            e.printStackTrace();
            throw new RuntimeException("Failed to upload the file: " + e.getMessage());
        } catch (Exception ex) {
            // Handle other exceptions
            ex.printStackTrace();
            throw new RuntimeException("An error occurred while uploading the file: " + ex.getMessage());
        }
    }
}
