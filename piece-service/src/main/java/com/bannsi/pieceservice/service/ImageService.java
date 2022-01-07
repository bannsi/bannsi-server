package com.bannsi.pieceservice.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bannsi.pieceservice.model.PieceImage;
import com.bannsi.pieceservice.repository.PieceImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ImageService {
    @Autowired
    private PieceImageRepository imageRepository;

    private final UploadService uploadService;

    public String uploadImage(MultipartFile multipartFile, Long pieceId){
        String filename = String.valueOf(pieceId) + "/" + createFilename(multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try (InputStream inputStream = multipartFile.getInputStream()){
            uploadService.uploadFile(inputStream, objectMetadata, filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러 발생  %s", multipartFile.getOriginalFilename()));
        }
        imageRepository.save(new PieceImage(pieceId, filename));
        return uploadService.getFileUrl(filename);
    }

    private String getFileExtension(String filename){
        try {
            return filename.substring(filename.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(String.format("잘못된 형식의 파일 %s 입니다.", filename));
        }
    }

    private String createFilename(String originalFilename){
        return UUID.randomUUID().toString().concat(getFileExtension(originalFilename));
    }

    public String downloadImage(String filename){
        return uploadService.getFileUrl(filename);
    }

    public List<String> getImageUrl(Long pieceId){
        List<PieceImage> pieceImages = imageRepository.findByPieceId(pieceId);
        List<String> images = new ArrayList<>();
        for(PieceImage pieceImage : pieceImages){
            images.add("https://jinho.s3.ap-northeast-2.amazonaws.com/" + pieceImage.getImageUrl());
        }
        return images;
    }
}
