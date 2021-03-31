package com.capelotto.springS3.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.capelotto.springS3.config.DownloadService;
import com.capelotto.springS3.entity.VideoItem;




@RestController
@RequestMapping("/v1")
public class S3Controller {

    @Autowired
    private DownloadService downloadService;

    @GetMapping(path = "/load", produces = "video/mp4")
    public ResponseEntity<InputStreamResource> create() throws ExecutionException, InterruptedException, IOException {

        VideoItem videoItem = downloadService.execute();

        InputStreamResource resource = new InputStreamResource(videoItem.getVideo());

        MediaType mediaType = MediaType.parseMediaType("video/mp4");


        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + videoItem.getExt() + "\"")
                .body(resource);
    }
    
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
    	downloadService.uploadS3Content(file);
        return "go";
    }
}