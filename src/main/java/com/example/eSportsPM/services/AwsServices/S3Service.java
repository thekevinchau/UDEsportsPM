package com.example.eSportsPM.services.AwsServices;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.nio.file.Paths;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String fileName, String filePath){
        try{
            PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(fileName).build();
            s3Client.putObject(request, RequestBody.fromFile(Paths.get(filePath)));
        } catch(S3Exception e){
            throw e;
        }
    }

    public void deleteFile(String fileName){
        try{
            DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(bucketName).key(fileName).build();
            s3Client.deleteObject(request);
        } catch(S3Exception e){
            throw e;
        }

    }

    public void downloadFile(String fileName, String downloadPath){
        final GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(fileName).build();
        s3Client.getObject(request, Paths.get(downloadPath));
    }

}
