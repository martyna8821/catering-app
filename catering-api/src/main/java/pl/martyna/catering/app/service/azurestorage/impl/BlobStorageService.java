package pl.martyna.catering.app.service.azurestorage.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.martyna.catering.app.service.azurestorage.IStorageService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Service("blobService")
public class BlobStorageService implements IStorageService {

    private final Logger LOG = LogManager.getLogger(getClass());
    private BlobServiceClient blobServiceClient;

    @Autowired
    public BlobStorageService(BlobServiceClient blobServiceClient){
        this.blobServiceClient = blobServiceClient;
    }


    @Override
    public String saveFile(String text) {

        String containerName = "quickstartblobs" + java.util.UUID.randomUUID();
        BlobContainerClient containerClient = blobServiceClient.createBlobContainer(containerName);
        String localPath = "./data/";
        String fileName = "quickstart" + java.util.UUID.randomUUID() + ".txt";
        File localFile = new File(localPath + fileName);

        try(FileWriter writer = new FileWriter(localPath + fileName, true)){
            writer.write(text);
        }catch(IOException ex){
            this.LOG.error("file exception");
        }

        BlobClient blobClient = containerClient.getBlobClient(fileName);

        this.LOG.info("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        blobClient.uploadFromFile(localPath + fileName);

        for (BlobItem blobItem : containerClient.listBlobs()) {
            this.LOG.info("\t" + blobItem.getName());
        }
        return fileName;
    }
}
