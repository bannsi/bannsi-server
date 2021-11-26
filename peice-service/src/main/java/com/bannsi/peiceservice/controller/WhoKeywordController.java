package com.bannsi.peiceservice.controller;

import com.bannsi.peiceservice.DTO.ResponseDTO;
import com.bannsi.peiceservice.model.WhoKeyword;
import com.bannsi.peiceservice.repository.WhoKeywordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/peices/v1/who")
public class WhoKeywordController {
    @Autowired
    private WhoKeywordRepository whoKeywordRepository;

    @RequestMapping(value="", method=RequestMethod.GET)
    public ResponseEntity<?> listWhos() {
        return ResponseEntity.ok().body(new ResponseDTO("list whoKeywords", whoKeywordRepository.findAll()));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createWho(@RequestBody WhoKeyword who){
        whoKeywordRepository.save(who);
        return ResponseEntity.ok().body(new ResponseDTO("whoKeyword is created", who));
    }   
}
