package com.bannsi.peiceservice.controller;

import java.util.List;

import com.bannsi.peiceservice.DTO.ResponseDTO;
import com.bannsi.peiceservice.model.Peice;
import com.bannsi.peiceservice.service.ImageService;
import com.bannsi.peiceservice.service.PeiceService;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javassist.NotFoundException;


@RestController
@RequestMapping(value = "v1/peice")
public class PeiceController {
    @Autowired
    private PeiceService peiceService;

    @Autowired
    private ImageService imageService;

    private static final Logger logger = LoggerFactory.getLogger(PeiceController.class);

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getPeicesByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(new ResponseDTO("get peices by user id", peiceService.getPeiceByUserId(userId)));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> savePeice(@RequestBody Peice peice, @RequestParam("images")List<MultipartFile> multipartFiles){
        peiceService.savePeice(peice, multipartFiles);
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
    
    @RequestMapping(value = "/test/", method = RequestMethod.POST)
    public ResponseEntity<?> testImage(@RequestPart MultipartFile file){
        String name = imageService.uploadImage(file);
        return ResponseEntity.ok().body(new ResponseDTO("file upload", name));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> testGetImage(@RequestParam String url){
        return ResponseEntity.ok().body(new ResponseDTO("image download", imageService.downloadImage(url)));
    }
}
