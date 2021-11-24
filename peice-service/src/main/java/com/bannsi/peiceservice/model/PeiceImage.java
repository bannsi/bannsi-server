package com.bannsi.peiceservice.model;

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
@Entity(name="peice_images")
@Table(name = "peice_images", indexes = @Index(name="peice_id", columnList = "peice_id"))
public class PeiceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="image_id", nullable = false)
    private Long imageId;
    @Column(name = "peice_id", nullable = false)
    private Long peiceId;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    public PeiceImage(Long peiceId, String imageUrl){
        this.peiceId = peiceId;
        this.imageUrl = imageUrl;
    }

    public PeiceImage withPeiceId(Long peiceId){
        this.setPeiceId(peiceId);
        return this;
    }

    public PeiceImage withImageUrl(String imageUrl){
        this.setImageUrl(imageUrl);
        return this;
    }
}
