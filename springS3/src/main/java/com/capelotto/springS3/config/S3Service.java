package com.capelotto.springS3.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

@Service
public class S3Service {
	
	public static final String MP3 = ".mp3";

    @Value("${s3.access.key}")
    private String accessKey;

    @Value("${s3.secret.key}")
    private String secretKey;

    @Value("${s3.host}")
    private String s3Host;

    @Value("${s3.bucket}")
    private String s3Bucket;
    
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    
   

    public S3ObjectInputStream download(String path) throws IOException {

        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secretKey
        );

        AmazonS3Client newClient = new AmazonS3Client(credentials,
                new ClientConfiguration().withSignerOverride("S3SignerType"));

        newClient.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
        newClient.setEndpoint(s3Host);

        S3Object object = newClient.getObject(s3Bucket, path);

        return object.getObjectContent();
    }
    
    public void uploadS3(MultipartFile multipartFile) throws IOException {     
    	 AWSCredentials credentials = new BasicAWSCredentials(
 	            accessKey,
 	            secretKey
 	    );
        AmazonS3Client newClient = new AmazonS3Client(credentials,
                new ClientConfiguration().withSignerOverride("S3SignerType"));
        File file = convertMultiPartToFile(multipartFile);
        String fileName = System.currentTimeMillis() + "_" + file.getName();
        newClient.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
        
        newClient.setEndpoint(s3Host);

        newClient.putObject(new PutObjectRequest(s3Bucket, fileName, file));
        file.delete();
    }
    
    public String deleteFile(String fileName) {
    	 AWSCredentials credentials = new BasicAWSCredentials(
  	            accessKey,
  	            secretKey
  	    );
         AmazonS3Client newClient = new AmazonS3Client(credentials,
                 new ClientConfiguration().withSignerOverride("S3SignerType"));
         newClient.setS3ClientOptions(S3ClientOptions.builder().setPathStyleAccess(true).build());
         newClient.setEndpoint(s3Host);
    	newClient.deleteObject(s3Bucket, fileName);
        return fileName + " removed ...";
    }

}