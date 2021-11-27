package com.bannsi.peiceservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bannsi.peiceservice.model.Keyword;
import com.bannsi.peiceservice.repository.KeywordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;

    public List<String> listKeywords(List<Long> Ids){
        List<String> keywords = new ArrayList<>();
        for(Long id : Ids){
            Optional<Keyword> keyword = keywordRepository.findById(id);
            if(keyword.isPresent()){
                keywords.add(keyword.get().getName());
            }
        }
        return keywords;
    }

    public void deletKeyword(Long keywordId){
        Optional<Keyword> keyword = keywordRepository.findById(keywordId);
        keywordRepository.delete(keyword.get());
    }
}
