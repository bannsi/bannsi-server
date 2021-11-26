package com.bansi.collectionservice.client;

import com.bansi.collectionservice.model.Peice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PeiceRestTemplateClient {
    @Autowired
    private RestTemplate restTemplate;

    public Peice getPeice(Long peiceId){
        ResponseEntity<Peice> restExchange = restTemplate.exchange("http://peiceservice/peices/v1/rt/{peiceId}", HttpMethod.GET, null, Peice.class, peiceId);
        return restExchange.getBody();
    }
}
