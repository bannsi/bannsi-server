package com.bannsi.pieceservice.controller;

import java.util.List;

import com.bannsi.pieceservice.DTO.PieceRequest;
import com.bannsi.pieceservice.DTO.PieceResponse;
import com.bannsi.pieceservice.DTO.ResponseDTO;
import com.bannsi.pieceservice.client.UserRestTemplateClient;
import com.bannsi.pieceservice.model.Piece;
import com.bannsi.pieceservice.model.User;
import com.bannsi.pieceservice.service.ImageService;
import com.bannsi.pieceservice.service.KeywordService;
import com.bannsi.pieceservice.service.PieceService;
import com.bannsi.pieceservice.util.JwtUtil;

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
@RequestMapping(value = "/pieces/v1")
public class PieceController {
    @Autowired
    private PieceService pieceService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private UserRestTemplateClient userRestTemplateClient;
    
    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(PieceController.class);

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getPiecesByUserId(@RequestHeader HttpHeaders headers){
        String token = headers.getFirst("Authorization").substring(7);
        String kakaoId = jwtUtil.getUsernameFromToken(token);
        logger.info(kakaoId);
        List<PieceResponse> pieceResponses = pieceService.findPieceByUserId(kakaoId);
        // List<PieceResponse> pieceResponses = new ArrayList<>();
        // User user = userRestTemplateClient.getUser(kakaoId);
        // for(Piece piece : pieces){
        //     pieceResponses.add(new PieceResponse(piece, user, imageService.getImageUrl(piece.getPieceId()), piece.getKeywords(), piece.getWhos()));
        // }
        return ResponseEntity.ok().body(new ResponseDTO("get pieces by user id", pieceResponses));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> savePiece(@RequestHeader HttpHeaders headers, @ModelAttribute PieceRequest pieceRequest){
        logger.info("create piece");
        String token = headers.getFirst("Authorization").substring(7);
        logger.info(token);
        String kakaoId = jwtUtil.getUsernameFromToken(token);
        logger.info(kakaoId);
        pieceRequest.setTitle("tmp title");
        Piece piece = pieceService.savePiece(pieceRequest, kakaoId);
        logger.info(piece.getAddress());
        User user = userRestTemplateClient.getUser(kakaoId);
        return ResponseEntity.ok().body(new ResponseDTO("piece is saved", new PieceResponse(piece, user, imageService.getImageUrl(piece.getPieceId()), piece.getKeywords(), piece.getWhos())));
    }

    @RequestMapping(value="/{pieceId}/", method=RequestMethod.PUT)
    public ResponseEntity<?> updatePiece(@PathVariable Long pieceId, @RequestBody Piece piece) {
        try {
            pieceService.updatePiece(pieceId, piece);    
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(new ResponseDTO(e.getMessage(), null));
        }
        return ResponseEntity.ok().body(new ResponseDTO("piece updated", null));
    }
}
