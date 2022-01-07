package com.bannsi.pieceservice.controller;

import java.util.Optional;

import com.bannsi.pieceservice.DTO.ResponseDTO;
import com.bannsi.pieceservice.model.WhoKeyword;
import com.bannsi.pieceservice.repository.WhoKeywordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/pieces/v1/who")
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

    @RequestMapping(value = "/{whoId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWho(@PathVariable Long whoId){
        Optional<WhoKeyword> whoKeyword = whoKeywordRepository.findById(whoId);
        whoKeywordRepository.delete(whoKeyword.get());
        return ResponseEntity.ok().body(new ResponseDTO("deleted", null));
    }
}
