package com.bansi.collectionservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bansi.collectionservice.DTO.PieceCollectionRequest;
import com.bansi.collectionservice.DTO.PieceCollectionResponse;
import com.bansi.collectionservice.DTO.ResponseDTO;
import com.bansi.collectionservice.model.PieceCollection;
import com.bansi.collectionservice.repository.ItemRepository;
import com.bansi.collectionservice.repository.PieceCollectionRepository;
import com.bansi.collectionservice.service.PieceCollectionService;
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
    private PieceCollectionService collectionService;

    @Autowired
    private PieceCollectionRepository collectionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createCollection(@RequestHeader HttpHeaders headers, @RequestBody PieceCollectionRequest collectionRequest){
        String token = headers.getFirst("Authorization").substring(7);
        String userId = jwtUtil.getUsernameFromToken(token);
        PieceCollection pieceCollection = collectionService.saveCollection(userId, collectionRequest);

        return ResponseEntity.ok().body(new ResponseDTO("test", pieceCollection));
    }

    @RequestMapping(value = "/{collectionId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCollection(@PathVariable Long collectionId) throws IOException{
        PieceCollectionResponse collectionResponse = null;
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
        List<PieceCollection> collections = collectionService.listCollections(userId);
        List<PieceCollectionResponse> collectionResponses = new ArrayList<>();
        for(PieceCollection collection : collections){
            try{
                collectionResponses.add(collectionService.getCollection(collection.getCollectionId()));
            } catch (Exception e){

            }
        }
        return ResponseEntity.ok().body(new ResponseDTO("collection list", collectionResponses));
    }
    
}
