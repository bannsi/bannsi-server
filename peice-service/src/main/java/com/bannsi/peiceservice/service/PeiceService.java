package com.bannsi.peiceservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bannsi.peiceservice.DTO.PeiceRequest;
import com.bannsi.peiceservice.model.Keyword;
import com.bannsi.peiceservice.model.Peice;
import com.bannsi.peiceservice.model.WhoKeyword;
import com.bannsi.peiceservice.repository.KeywordRepository;
import com.bannsi.peiceservice.repository.PeiceImageRepository;
import com.bannsi.peiceservice.repository.PeiceRepository;
import com.bannsi.peiceservice.repository.WhoKeywordRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javassist.NotFoundException;

@Service
public class PeiceService {
    @Autowired
    private PeiceRepository peiceRepository;
    
    @Autowired
    private PeiceImageRepository imageRepository;

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    private WhoKeywordRepository whoKeywordRepository;

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(PeiceRepository.class);

    // public Peice getPieceByPeiceId(Long peiceId){

    // }

    public List<Peice> findPeiceByUserId(String userId){
        return peiceRepository.findByUserId(userId);
    }

    public Peice updatePeice(Long peiceId, Peice peice) throws NotFoundException {
        Optional<Peice> oPeice = peiceRepository.findById(peiceId);
        if(!oPeice.isPresent()) throw new NotFoundException(String.valueOf(peiceId) + " is not found");
        oPeice.get().withTitle(peice.getTitle())
            .withContent(peice.getContent())
            .withLatitude(peice.getLatitude())
            .withLongitude(peice.getLongitude());
        peiceRepository.save(oPeice.get());
        return oPeice.get();
    }

    public Peice savePeice(PeiceRequest peiceRequest, String userId){
        Peice peice = new Peice()
            .withUserId(userId)
            .withTitle(peiceRequest.getTitle())
            .withDate(peiceRequest.getDate())
            .withContent(peiceRequest.getContent())
            .withLatitude(peiceRequest.getLatitude())
            .withLongitude(peiceRequest.getLongitude())
            .withPlaceUrl(peiceRequest.getPlaceUrl())
            .withAddress(peiceRequest.getAddress())
            .withAddressDetail(peiceRequest.getAddressDetail());
        List<Keyword> keywords = new ArrayList<Keyword>();
        
        for(Long keyword_id : peiceRequest.getKeywords()){
            Optional<Keyword> keyword = keywordRepository.findById(keyword_id);
            if(keyword.isPresent()){
                keywords.add(keyword.get());
            }
        }
        peice.setKeywords(keywords);

        List<WhoKeyword> whos = new ArrayList<WhoKeyword>();
        for(Long who_id : peiceRequest.getWhos()){
            Optional<WhoKeyword> who = whoKeywordRepository.findById(who_id);
            if(who.isPresent()){
                whos.add(who.get());
            }
        }
        peice.setWhos(whos);
        
        peiceRepository.save(peice.withCreatedAt(new Date()));
        
        for(MultipartFile file : peiceRequest.getImages()){
            if(file.getOriginalFilename().length() != 0)
                imageService.uploadImage(file, peice.getPeiceId());
        }
        return peice;
    }
}
