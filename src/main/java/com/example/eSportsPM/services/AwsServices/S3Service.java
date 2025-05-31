package com.example.eSportsPM.services.AwsServices;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.nio.file.Paths;
import java.time.Duration;

@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.access.key}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    /*
        This function generates a presigned URL where the user specifies the objectKey name to store inside the S3 bucket.
            - The user will upload their file from the client side, and then we make a call to the backend using the name of this file.
            - This will generate a presigned URL that we send back to the client and then the client sends a PUT request to that URL.
            - Then this will upload that image to the bucket.

     */
    public ResponseEntity<String> generatePreSignedUrlUpload(String objectKey){
        //Generate a presigner with our AWS Credentials.
        S3Presigner presigner = S3Presigner.
                builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        //Build the PutObjectRequest using the bucket name and key
        PutObjectRequest request = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();


        //Generate the presignedURL from the request and sign it with a duration of 10 minutes.
        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(builder -> builder.putObjectRequest(request).signatureDuration(Duration.ofMinutes(10)));
        return ResponseEntity.ok(presignedRequest.url().toString());
    }

        /*
        This function generates a presigned URL where the user specifies the objectKey name to retrieve inside the S3 bucket.
            - The client will make a request to our server that executes this function using the keyName of the avatar stored in the database.
            - This will generate a presigned URL that we send back to the client and then the client sends a GET request to that URL.
            - Then this will retrieve the image to the bucket.

     */
    public ResponseEntity<String> generatePreSignedUrlDownload(String objectKey){
        S3Presigner presigner = S3Presigner.
                builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey)))
                .build();

        GetObjectRequest request = GetObjectRequest
                .builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(
                builder -> builder.getObjectRequest(request).signatureDuration(Duration.ofMinutes(10)));

        return ResponseEntity.ok(presignedRequest.url().toString());
    }

}
