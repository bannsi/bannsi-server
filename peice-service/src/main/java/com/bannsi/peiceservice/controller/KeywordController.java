package com.bannsi.peiceservice.controller;

import java.util.List;

import com.bannsi.peiceservice.DTO.ResponseDTO;
import com.bannsi.peiceservice.model.Keyword;
import com.bannsi.peiceservice.repository.KeywordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/peices/v1/keyword")
public class KeywordController {
    @Autowired
    private KeywordRepository keywordRepository;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> listKeywords(){
        List<Keyword> keywords = keywordRepository.findAll();
        return ResponseEntity.ok().body(new ResponseDTO("keywords", keywords));
    }
}
