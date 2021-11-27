package com.bansi.collectionservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bansi.collectionservice.DTO.PeiceCollectionRequest;
import com.bansi.collectionservice.DTO.PeiceCollectionResponse;
import com.bansi.collectionservice.DTO.ResponseDTO;
import com.bansi.collectionservice.model.PeiceCollection;
import com.bansi.collectionservice.repository.ItemRepository;
import com.bansi.collectionservice.repository.PeiceCollectionRepository;
import com.bansi.collectionservice.service.PeiceCollectionService;
import com.bansi.collectionservice.utils.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/collections/v1")
public class CollectionController {
    private final Logger logger = LoggerFactory.getLogger(CollectionController.class);

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private PeiceCollectionService collectionService;

    @Autowired
    private PeiceCollectionRepository collectionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createCollection(@RequestHeader HttpHeaders headers, @RequestBody PeiceCollectionRequest collectionRequest){
        String token = headers.getFirst("Authorization").substring(7);
        String userId = jwtUtil.getUsernameFromToken(token);
        PeiceCollection peiceCollection = collectionService.saveCollection(userId, collectionRequest);

        return ResponseEntity.ok().body(new ResponseDTO("test", peiceCollection));
    }

    @RequestMapping(value = "/{collectionId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCollection(@PathVariable Long collectionId) throws IOException{
        PeiceCollectionResponse collectionResponse = null;
        // collectionResponse = collectionService.getCollection(collectionId);    
        try {
            collectionResponse = collectionService.getCollection(collectionId);    
        } catch (Exception e) {
            ResponseEntity.badRequest().body(new ResponseDTO("wrong collection id: " + String.valueOf(collectionId), null));
        }
        return ResponseEntity.ok().body(new ResponseDTO("get collection", collectionResponse));
    }

    @RequestMapping(value="/user/{userId}", method=RequestMethod.GET)
    public ResponseEntity<?> listCollection(@PathVariable String userId) {
        List<PeiceCollection> collections = collectionService.listCollections(userId);
        List<PeiceCollectionResponse> collectionResponses = new ArrayList<>();
        for(PeiceCollection collection : collections){
            try{
                collectionResponses.add(collectionService.getCollection(collection.getCollectionId()));
            } catch (Exception e){

            }
        }
        return ResponseEntity.ok().body(new ResponseDTO("collection list", collectionResponses));
    }
    
}
