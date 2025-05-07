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

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        //Saving file temporarily on server before uploading to s3
        String keyName = file.getOriginalFilename(); //key name for s3
        File tempFile = File.createTempFile("temp", null);
        file.transferTo(tempFile); //transfers the inputted file to the tempfile on the server.

        s3Service.uploadFile(keyName, tempFile.getAbsolutePath());
        return "File Uploaded Successfully!";
    }

}
