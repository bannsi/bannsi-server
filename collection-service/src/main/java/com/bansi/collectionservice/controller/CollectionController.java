package com.bansi.collectionservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bansi.collectionservice.DTO.PCollectionDTO;
import com.bansi.collectionservice.DTO.ResponseDTO;
import com.bansi.collectionservice.model.Item;
import com.bansi.collectionservice.model.PeiceCollection;
import com.bansi.collectionservice.repository.ItemRepository;
import com.bansi.collectionservice.repository.PeiceCollectionRepository;
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
    private PeiceCollectionRepository collectionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createCollection(@RequestHeader HttpHeaders headers, @RequestBody PCollectionDTO collectionDTO){
        String token = headers.getFirst("Authorization").substring(7);
        String userId = jwtUtil.getUsernameFromToken(token);
        Map<Date, List<Item>> dateItemMap = new HashMap<>();
        List<Item> totalItems = new ArrayList<>();
        for(Item item : collectionDTO.getItems()){
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
        peiceCollection.setTitle(collectionDTO.getTitle());
        peiceCollection.setUserId(userId);
        peiceCollection.setItems(totalItems);
        itemRepository.saveAll(totalItems);
        collectionRepository.save(peiceCollection);
        return ResponseEntity.ok().body(new ResponseDTO("test", peiceCollection));
    }

    @RequestMapping(value = "/{collectionId}", method = RequestMethod.GET)
    public ResponseEntity<?> listCollection(@PathVariable Long collectionId){
        Optional<PeiceCollection> opColleciton = collectionRepository.findByCollectionId(collectionId);
        if(!opColleciton.isPresent()){
            return ResponseEntity.badRequest().body(new ResponseDTO("wrong collection id: " + String.valueOf(collectionId), null));
        }
        return ResponseEntity.ok().body(new ResponseDTO("get collection", opColleciton.get()));
    }
}
