package com.bannsi.peiceservice.controller;

import java.util.ArrayList;
import java.util.List;

import com.bannsi.peiceservice.DTO.PeiceRequest;
import com.bannsi.peiceservice.DTO.PeiceResponse;
import com.bannsi.peiceservice.DTO.ResponseDTO;
import com.bannsi.peiceservice.model.Peice;
import com.bannsi.peiceservice.service.ImageService;
import com.bannsi.peiceservice.service.PeiceService;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;


@RestController
@RequestMapping(value = "/peices/v1")
public class PeiceController {
    @Autowired
    private PeiceService peiceService;

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(PeiceController.class);

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPeicesByUserId(@PathVariable String userId){
        List<Peice> peices = peiceService.findPeiceByUserId(userId);
        List<PeiceResponse> peiceResponses = new ArrayList<>();
        for(Peice peice : peices){
            peiceResponses.add(new PeiceResponse(peice, imageService.getImageUrl(peice.getPeiceId())));
        }
        return ResponseEntity.ok().body(new ResponseDTO("get peices by user id", peiceResponses));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> savePeice(@ModelAttribute PeiceRequest peiceRequest){
        peiceService.savePeice(peiceRequest, "test user id");
        return ResponseEntity.ok().body(new ResponseDTO("peice is saved", null));
    }

    @RequestMapping(value="/{peiceId}/", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePeice(@PathVariable Long peiceId, @RequestBody Peice peice) {
        try {
            peiceService.updatePeice(peiceId, peice);    
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(new ResponseDTO(e.getMessage(), null));
        }
        return ResponseEntity.ok().body(new ResponseDTO("peice updated", null));
    }
}
