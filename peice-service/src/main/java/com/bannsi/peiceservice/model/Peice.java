package com.bannsi.peiceservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="peices")
public class Peice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="peice_id", nullable = false)    
    private Long peiceId;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="content")
    private String content;
    @CreatedDate
    @Column(name="created_at", nullable = false)
    private Date createdAt;
    @Column(name = "latitude", nullable = false)
    private Long latitude;
    @Column(name = "longitude", nullable = false)
    private Long longitude;

    public Peice withTitle(String title){
        this.setTitle(title);
        return this;
    }
    
    public Peice withContent(String content){
        this.setContent(content);
        return this;
    }

    public Peice withLatitude(Long latitude){
        this.setLatitude(latitude);
        return this;
    }

    public Peice withLongitude(Long longitude){
        this.setLongitude(longitude);
        return this;
    }

    public Peice withCreatedAt(Date createdAt){
        this.setCreatedAt(createdAt);
        return this;   
    }
}
