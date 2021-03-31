package com.capelotto.springS3.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.capelotto.springS3.entity.VideoItem;



@Service
public class DownloadService {

	@Autowired
    private S3Service s3Service;

    @Value("${video.path}")
    private String path;

    @Value("${video.size}")
    private String size;

    @Value("${video.ext}")
    private String ext;

    public VideoItem execute() throws IOException {
        return VideoItem
                .builder()
                .video(s3Service.execute(path))
                .ext(ext)
                .build();
    }

    
    
    public void uploadS3Content(MultipartFile file) throws IOException {
    	s3Service.uploadS3(file);
    }

}
