package com.bannsi.pieceservice.service;


import java.io.InputStream;

import com.amazonaws.services.s3.model.ObjectMetadata;

public interface UploadService {
    void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String filename);
    String getFileUrl(String filename);
}
