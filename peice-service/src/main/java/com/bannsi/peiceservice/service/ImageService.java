package com.bannsi.peiceservice.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.amazonaws.services.s3.model.ObjectMetadata;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final UploadService uploadService;

    public String uploadImage(MultipartFile multipartFile){
        String filename = createFilename(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try (InputStream inputStream = multipartFile.getInputStream()){
            uploadService.uploadFile(inputStream, objectMetadata, filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러 발생  {}", multipartFile.getOriginalFilename()));
        }
        return uploadService.getFileUrl(filename);
    }

    private String getFileExtension(String filename){
        try {
            return filename.substring(filename.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 {} 입니다.", filename));
        }
    }

    private String createFilename(String originalFilename){
        return UUID.randomUUID().toString().concat(getFileExtension(originalFilename));
    }

    public String downloadImage(String filename){
        return uploadService.getFileUrl(filename);
    }
}
