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
import com.bansi.collectionservice.DTO.PeiceCollectionRequest;
import com.bansi.collectionservice.DTO.PeiceCollectionResponse;
import com.bansi.collectionservice.client.PeiceRestTemplateClient;
import com.bansi.collectionservice.model.Item;
import com.bansi.collectionservice.model.Peice;
import com.bansi.collectionservice.model.PeiceCollection;
import com.bansi.collectionservice.repository.ItemRepository;
import com.bansi.collectionservice.repository.PeiceCollectionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PeiceCollectionService {
    private static final Logger logger = LoggerFactory.getLogger(PeiceCollectionService.class);
    
    @Autowired
    private PeiceCollectionRepository collectionRepository;

    @Autowired
    private PeiceRestTemplateClient peiceRestTemplateClient;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ItemRepository itemRepository;

    private Peice getPeice(Long peiceId){
        Peice peice = peiceRestTemplateClient.getPeice(peiceId);
        return peice;
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

    public PeiceCollectionResponse getCollection(Long collectionId) throws IOException {
        Optional<PeiceCollection> peiceCollection = collectionRepository.findByCollectionId(collectionId);
        logger.info(peiceCollection.get().getTitle());
        if(!peiceCollection.isPresent()) throw new IOException();
        // List<Peice> peices = new ArrayList<Peice>();
        List<ItemResponse> items = new ArrayList<ItemResponse>();
        for(Item item : peiceCollection.get().getItems()){
            Peice peice = null;
            if(item.getPeiceId() != null)
                peice = getPeice(item.getPeiceId());
            items.add(new ItemResponse(item.getContent(), peice, item.getOrderNum(), item.getDate()));

            // peices.add(getPeice(item.getPeiceId()));
        }
        return new PeiceCollectionResponse(
            peiceCollection.get().getCollectionId(),
            peiceCollection.get().getTitle(), 
            peiceCollection.get().getCoverImage(),
            peiceCollection.get().getStartDate(),
            peiceCollection.get().getEndDate(),
            items);
    }

    public PeiceCollection saveCollection(String userId, PeiceCollectionRequest collectionRequest){
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
        PeiceCollection peiceCollection = new PeiceCollection();
        peiceCollection.setTitle(collectionRequest.getTitle());
        peiceCollection.setUserId(userId);
        peiceCollection.setStartDate(collectionRequest.getStartDate());
        peiceCollection.setEndDate(collectionRequest.getEndDate());
        peiceCollection.setItems(totalItems);
        peiceCollection.setCoverImage(collectionRequest.getCoverImage());
        itemRepository.saveAll(totalItems);
        // String filename = UUID.randomUUID().toString() + "/" + createFilename(collectionRequest.getCoverImage().getOriginalFilename());
        
        collectionRepository.save(peiceCollection);   

        // uploadImage(collectionRequest.getCoverImage(), filename);

        return peiceCollection;
    }

    public List<PeiceCollection> listCollections(String userId){
        List<PeiceCollection> collections = collectionRepository.findByUserId(userId);
        return collections;   
    }
}
