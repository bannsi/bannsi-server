package com.bansi.collectionservice.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bansi.collectionservice.client.PeiceRestTemplateClient;
import com.bansi.collectionservice.model.Item;
import com.bansi.collectionservice.model.Peice;
import com.bansi.collectionservice.model.PeiceCollection;
import com.bansi.collectionservice.repository.PeiceCollectionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeiceCollectionService {
    private static final Logger logger = LoggerFactory.getLogger(PeiceCollectionService.class);
    
    @Autowired
    private PeiceCollectionRepository collectionRepository;

    @Autowired
    private PeiceRestTemplateClient peiceRestTemplateClient;

    private Peice getPeice(Long peiceId){
        Peice peice = peiceRestTemplateClient.getPeice(peiceId);
        return peice;
    }

    public PeiceCollection getCollection(Long collectionId) throws IOException {
        Optional<PeiceCollection> peiceCollection = collectionRepository.findByCollectionId(collectionId);
        if(peiceCollection.isPresent()) throw new IOException();
        List<Peice> peice = new ArrayList<Peice>();
        for(Item item : peiceCollection.get().getItems()){
            peice.add(getPeice(item.getPeiceId()));
        }
        return peiceCollection.get();
    }
}
