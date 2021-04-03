package com.capelotto.springS3.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.capelotto.springS3.config.S3Connection;
import com.capelotto.springS3.entity.fileModel;


@RestController
@RequestMapping("/s3")
public class S3Controller {

    @Autowired
    private S3Connection downloadService;
    
   
    
    @GetMapping(path = "/video/{file}", produces = "video/mp4")
    public ResponseEntity<InputStreamResource> create(@PathVariable String file) throws ExecutionException, InterruptedException, IOException {

    	fileModel fileResult = downloadService.downloadS3(file);

        InputStreamResource resource = new InputStreamResource(fileResult.getFile());

        MediaType mediaType = MediaType.parseMediaType("video/mp4");


        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResult.getExt() + "\"")
                .body(resource);
    }

    @GetMapping(path = "/downloadJPG/{file}")
    public ResponseEntity<InputStreamResource> downloadJPG(@PathVariable String file) throws ExecutionException, InterruptedException, IOException {

    	 fileModel fileResult = downloadService.downloadS3(file);

         InputStreamResource resource = new InputStreamResource(fileResult.getFile());

        MediaType mediaType = MediaType.parseMediaType("image/jpeg");


        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResult.getExt() + "\"")
                .body(resource);
    }
    
    @GetMapping(path = "/downloadPDF/{file}")
    public ResponseEntity<InputStreamResource> downloadPDF(@PathVariable String file) throws ExecutionException, InterruptedException, IOException {

    	 fileModel fileResult = downloadService.downloadS3(file);

         InputStreamResource resource = new InputStreamResource(fileResult.getFile());
         
        MediaType mediaType = MediaType.parseMediaType("application/pdf");


        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResult.getExt() + "\"")
                .body(resource);
    }
    
    @GetMapping(path = "/downloadMP3/{file}")
    public ResponseEntity<InputStreamResource> downloadMP3(@PathVariable String file) throws ExecutionException, InterruptedException, IOException {

        fileModel fileResult = downloadService.downloadS3(file);

        InputStreamResource resource = new InputStreamResource(fileResult.getFile());

        MediaType mediaType = MediaType.parseMediaType("audio/mpeg3");


        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileResult.getExt() + "\"")
                .body(resource);
    }
    
    
    @PostMapping("/upload")
    public ResponseEntity<fileModel> uploadFile(@RequestParam(value = "file") MultipartFile file)throws IOException {	
    	downloadService.uploadS3Content(file);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
    	downloadService.deleteS3(fileName);
    	return new ResponseEntity(HttpStatus.OK);
    }
}