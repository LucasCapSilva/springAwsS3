package com.capelotto.springS3.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capelotto.springS3.entity.fileModel;



@Service
public class S3Connection {
	
	@Autowired
    private S3Service s3Service;
	
    public fileModel downloadS3(String path) throws IOException {
        return fileModel
        		.builder()
                .file(s3Service.download(path))
                .ext(path)
                .build();
    }

    
    
    public void uploadS3Content(MultipartFile file) throws IOException {
    	s3Service.uploadS3(file);
    }
    
    public void deleteS3(String file){
    	s3Service.deleteFile(file);
    }

}
