package com.bansi.collectionservice.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.bansi.collectionservice.DTO.ItemResponse;
import com.bansi.collectionservice.DTO.PieceCollectionRequest;
import com.bansi.collectionservice.DTO.PieceCollectionResponse;
import com.bansi.collectionservice.client.PieceRestTemplateClient;
import com.bansi.collectionservice.model.Item;
import com.bansi.collectionservice.model.Piece;
import com.bansi.collectionservice.model.PieceCollection;
import com.bansi.collectionservice.repository.ItemRepository;
import com.bansi.collectionservice.repository.PieceCollectionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PieceCollectionService {
    private static final Logger logger = LoggerFactory.getLogger(PieceCollectionService.class);
    
    @Autowired
    private PieceCollectionRepository collectionRepository;

    @Autowired
    private PieceRestTemplateClient pieceRestTemplateClient;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ItemRepository itemRepository;

    private Piece getPiece(Long pieceId){
        Piece piece = pieceRestTemplateClient.getPiece(pieceId);
        return piece;
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

    private String uploadImage(MultipartFile multipartFile, String filename){
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        try (InputStream inputStream = multipartFile.getInputStream()){
            uploadService.uploadFile(inputStream, objectMetadata, filename);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format("파일 변환 중 에러 발생  %s", multipartFile.getOriginalFilename()));
        }
        return uploadService.getFileUrl(filename);
    }

    public PieceCollectionResponse getCollection(Long collectionId) throws IOException {
        Optional<PieceCollection> pieceCollection = collectionRepository.findByCollectionId(collectionId);
        logger.info(pieceCollection.get().getTitle());
        if(!pieceCollection.isPresent()) throw new IOException();
        // List<Piece> pieces = new ArrayList<Piece>();
        List<ItemResponse> items = new ArrayList<ItemResponse>();
        for(Item item : pieceCollection.get().getItems()){
            Piece piece = null;
            if(item.getPieceId() != null)
                piece = getPiece(item.getPieceId());
            items.add(new ItemResponse(item.getContent(), piece, item.getOrderNum(), item.getDate()));

            // pieces.add(getPiece(item.getPieceId()));
        }
        return new PieceCollectionResponse(
            pieceCollection.get().getCollectionId(),
            pieceCollection.get().getTitle(), 
            pieceCollection.get().getCoverImage(),
            pieceCollection.get().getStartDate(),
            pieceCollection.get().getEndDate(),
            items);
    }

    public PieceCollection saveCollection(String userId, PieceCollectionRequest collectionRequest){
        Map<Date, List<Item>> dateItemMap = new HashMap<>();
        List<Item> totalItems = new ArrayList<>();
        for(Item item : collectionRequest.getItems()){
            if(dateItemMap.containsKey(item.getDate())){
                // item.setOrderNum(dateItemMap.get(item.getDate()).size());
                dateItemMap.get(item.getDate()).add(item);
            } else {
                // item.setOrderNum(0);
                List<Item> items = new ArrayList<>();
                items.add(item);
                dateItemMap.put(item.getDate(), items);
            }
            totalItems.add(item);
        }
        PieceCollection pieceCollection = new PieceCollection();
        pieceCollection.setTitle(collectionRequest.getTitle());
        pieceCollection.setUserId(userId);
        pieceCollection.setStartDate(collectionRequest.getStartDate());
        pieceCollection.setEndDate(collectionRequest.getEndDate());
        pieceCollection.setItems(totalItems);
        pieceCollection.setCoverImage(collectionRequest.getCoverImage());
        itemRepository.saveAll(totalItems);
        // String filename = UUID.randomUUID().toString() + "/" + createFilename(collectionRequest.getCoverImage().getOriginalFilename());
        
        collectionRepository.save(pieceCollection);   

        // uploadImage(collectionRequest.getCoverImage(), filename);

        return pieceCollection;
    }

    public List<PieceCollection> listCollections(String userId){
        List<PieceCollection> collections = collectionRepository.findByUserId(userId);
        return collections;   
    }
}
