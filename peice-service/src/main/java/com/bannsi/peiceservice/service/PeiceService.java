package com.bannsi.peiceservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bannsi.peiceservice.DTO.PeiceRequest;
import com.bannsi.peiceservice.model.Peice;
import com.bannsi.peiceservice.repository.PeiceImageRepository;
import com.bannsi.peiceservice.repository.PeiceRepository;

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
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(PeiceRepository.class);

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
            .withContent(peiceRequest.getContent())
            .withLatitude(peiceRequest.getLatitude())
            .withLongitude(peiceRequest.getLongitude());
        peiceRepository.save(peice.withCreatedAt(new Date()));
        
        for(MultipartFile file : peiceRequest.getImages()){
            if(file.getOriginalFilename().length() != 0)
                imageService.uploadImage(file, peice.getPeiceId());
        }
        return peice;
    }
}
