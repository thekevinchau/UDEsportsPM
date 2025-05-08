package com.example.eSportsPM.controllers;

import com.example.eSportsPM.services.AwsServices.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/s3")
@AllArgsConstructor
public class S3Controller {

    private final S3Service s3Service;

    @GetMapping("/generate-presigned-url-upload")
    public String generatePresignedUrlUpload(@RequestParam String key){
        return s3Service.generatePreSignedUrlUpload(key);
    }

    @GetMapping("/generate-presigned-url-download")
    public String generatePresignedUrlDownload(@RequestParam String key){
        return s3Service.generatePreSignedUrlDownload(key);
    }

}
