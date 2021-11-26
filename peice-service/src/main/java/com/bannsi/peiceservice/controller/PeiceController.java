package com.bannsi.peiceservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bannsi.peiceservice.DTO.PeiceRequest;
import com.bannsi.peiceservice.DTO.PeiceResponse;
import com.bannsi.peiceservice.DTO.ResponseDTO;
import com.bannsi.peiceservice.model.Peice;
import com.bannsi.peiceservice.service.ImageService;
import com.bannsi.peiceservice.service.KeywordService;
import com.bannsi.peiceservice.service.PeiceService;
import com.bannsi.peiceservice.util.JwtUtil;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(PeiceController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getPeicesByUserId(@RequestHeader HttpHeaders headers){
        String token = headers.getFirst("Authorization").substring(7);
        String kakaoId = jwtUtil.getUsernameFromToken(token);
        logger.info(kakaoId);
        List<Peice> peices = peiceService.findPeiceByUserId(kakaoId);
        List<PeiceResponse> peiceResponses = new ArrayList<>();
        for(Peice peice : peices){
            peiceResponses.add(new PeiceResponse(peice, imageService.getImageUrl(peice.getPeiceId()), peice.getKeywords(), peice.getWhos()));
        }
        return ResponseEntity.ok().body(new ResponseDTO("get peices by user id", peiceResponses));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> savePeice(@RequestHeader HttpHeaders headers, @ModelAttribute PeiceRequest peiceRequest){
        String token = headers.getFirst("Authorization").substring(7);
        String kakaoId = jwtUtil.getUsernameFromToken(token);
        peiceRequest.setTitle("tmp title");
        Peice peice = peiceService.savePeice(peiceRequest, kakaoId);

        return ResponseEntity.ok().body(new ResponseDTO("peice is saved", new PeiceResponse(peice, imageService.getImageUrl(peice.getPeiceId()), peice.getKeywords(), peice.getWhos())));
    }
    // TODO: restTemplate get Peice from peiceId
    @RequestMapping(value = "/{peiceId}", method = RequestMethod.GET)
    public PeiceResponse getPeice(@PathVariable Long peiceId) throws IOException {
        Peice peice =  peiceService.getPieceByPeiceId(peiceId);
        return new PeiceResponse(peice, imageService.getImageUrl(peice.getPeiceId()), peice.getKeywords(), peice.getWhos());
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
