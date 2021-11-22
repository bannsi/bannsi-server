package com.bannsi.peiceservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="peice_images")
public class PeiceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="image_id", nullable = false)
    private Long imageId;
}
