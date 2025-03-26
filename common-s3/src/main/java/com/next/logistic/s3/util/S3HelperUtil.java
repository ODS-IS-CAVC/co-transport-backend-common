package com.next.logistic.s3.util;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.next.logistic.exception.NextWebException;
import com.next.logistic.exception.model.NextAPIError;
import com.next.logistic.model.SoaResponsePool;
import com.next.logistic.s3.config.AwsConfig;
import com.next.logistic.s3.config.AwsConfigProperties;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class S3HelperUtil {

    @Autowired
    private AwsConfigProperties awsConfigProperties;

    @Autowired
    private AwsConfig awsConfig;
    public  void upload(MultipartFile file, String fileName) {
        AmazonS3 s3Client = awsConfig.getS3Client();
        try {
            s3Client.putObject(new PutObjectRequest(awsConfigProperties.getBucketName(), fileName, file.getInputStream(), null));
        } catch (IOException e) {
            throw new NextWebException(
                    new NextAPIError(HttpStatus.BAD_REQUEST, SoaResponsePool.get("upload_error"), fileName));
        }
    }

    public List<String> getUrl(List<String> fileNameList) {
        AmazonS3 s3Client = awsConfig.getS3Client();
        List<String> urlList = new ArrayList<>();
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += awsConfigProperties.getExpiredTime(); //default 1 hour
        expiration.setTime(expTimeMillis);
        for (String fileName : fileNameList) {
            if (!s3Client.doesObjectExist(awsConfigProperties.getBucketName(), fileName)) {
                throw new NextWebException(
                        new NextAPIError(HttpStatus.BAD_REQUEST, SoaResponsePool.get("file_not_exist"), fileName));
            }
            GeneratePresignedUrlRequest generatePresignedUrlRequest =
                    new GeneratePresignedUrlRequest(awsConfigProperties.getBucketName(), fileName)
                            .withMethod(HttpMethod.GET)
                            .withExpiration(expiration);
            String fileUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest).toString();
            urlList.add(fileUrl);
        }
        return urlList;
    }

    public byte[] download(String fileName) throws IOException {
        AmazonS3 s3Client = awsConfig.getS3Client();
        if (!s3Client.doesObjectExist(awsConfigProperties.getBucketName(), fileName)) {
            throw new NextWebException(
                    new NextAPIError(HttpStatus.BAD_REQUEST, SoaResponsePool.get("file_not_exist"), fileName));
        }
        S3Object s3Object = s3Client.getObject(awsConfigProperties.getBucketName(), fileName);
        try (InputStream inputStream = s3Object.getObjectContent()) {
            return inputStream.readAllBytes();
        }
    }
}
