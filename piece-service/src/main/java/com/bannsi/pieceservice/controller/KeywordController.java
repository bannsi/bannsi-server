package com.bannsi.pieceservice.controller;

import java.util.List;

import com.bannsi.pieceservice.DTO.ResponseDTO;
import com.bannsi.pieceservice.model.Keyword;
import com.bannsi.pieceservice.repository.KeywordRepository;
import com.bannsi.pieceservice.service.KeywordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pieces/v1/keyword")
public class KeywordController {
    @Autowired
    private KeywordRepository keywordRepository;
    
    @Autowired
    private KeywordService keywordService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> listKeywords(){
        List<Keyword> keywords = keywordRepository.findAll();
        return ResponseEntity.ok().body(new ResponseDTO("keywords", keywords));
    }
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createKeyword(@RequestBody Keyword keyword){
        keywordRepository.save(keyword);
        return ResponseEntity.ok().body(new ResponseDTO("keyword is created", keyword));
    }

    @RequestMapping(value = "/{keywordId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteKeyword(@PathVariable Long keywordId){
        keywordService.deletKeyword(keywordId);
        return ResponseEntity.ok().body(new ResponseDTO("delete", null));
    }
}
