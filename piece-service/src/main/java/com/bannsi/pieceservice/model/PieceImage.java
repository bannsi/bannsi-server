package com.bannsi.pieceservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name="piece_images")
@Table(name = "piece_images", indexes = @Index(name="piece_id", columnList = "piece_id"))
public class PieceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="image_id", nullable = false)
    private Long imageId;
    @Column(name = "piece_id", nullable = false)
    private Long pieceId;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public PieceImage(Long pieceId, String imageUrl){
        this.pieceId = pieceId;
        this.imageUrl = imageUrl;
    }

    public PieceImage withPieceId(Long pieceId){
        this.setPieceId(pieceId);
        return this;
    }

    public PieceImage withImageUrl(String imageUrl){
        this.setImageUrl(imageUrl);
        return this;
    }
}
