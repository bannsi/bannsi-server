package com.bannsi.peiceservice.controller;

import java.io.IOException;

import com.bannsi.peiceservice.DTO.PeiceResponse;
import com.bannsi.peiceservice.model.Peice;
import com.bannsi.peiceservice.service.ImageService;
import com.bannsi.peiceservice.service.PeiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "peices/v1/rt")
public class PeiceRestTemplateController {
    @Autowired
    private PeiceService peiceService;

    @Autowired
    private ImageService imageService;
    
    // TODO: restTemplate get Peice from peiceId
    @RequestMapping(value = "/{peiceId}", method = RequestMethod.GET)
    public PeiceResponse getPeice(@PathVariable Long peiceId) throws IOException {
        Peice peice =  peiceService.getPieceByPeiceId(peiceId);
        return new PeiceResponse(peice, imageService.getImageUrl(peice.getPeiceId()), peice.getKeywords(), peice.getWhos());
    }
}
