package com.bannsi.peiceservice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    @Column(name = "date", nullable = false)
    private Date date;
    @CreatedDate
    @Column(name="created_at", nullable = false)
    private Date createdAt;
    @Column(name = "latitude", nullable = false)
    private Double latitude;
    @Column(name = "longitude", nullable = false)
    private Double longitude;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "address_detail", nullable = false)
    private String addressDetail;
    @Column(name = "place_url", nullable = false)
    private String placeUrl;
    @ManyToMany
    private List<Keyword> keywords;
    @ManyToMany
    private List<WhoKeyword> whos;
    
    
    public Peice withUserId(String userId){
        this.setUserId(userId);
        return this;
    }
    
    public Peice withTitle(String title){
        this.setTitle(title);
        return this;
    }
    
    public Peice withDate(Date date){
        this.setDate(date);
        return this;
    }

    public Peice withContent(String content){
        this.setContent(content);
        return this;
    }

    public Peice withLatitude(Double latitude){
        this.setLatitude(latitude);
        return this;
    }

    public Peice withLongitude(Double longitude){
        this.setLongitude(longitude);
        return this;
    }

    public Peice withAddress(String address){
        this.setAddress(address);
        return this;
    }

    public Peice withAddressDetail(String addressDetail){
        this.setAddressDetail(addressDetail);
        return this;
    }

    public Peice withPlaceUrl(String placeUrl){
        this.placeUrl = placeUrl;
        return this;
    }

    public Peice withCreatedAt(Date createdAt){
        this.setCreatedAt(createdAt);
        return this;   
    }
}
