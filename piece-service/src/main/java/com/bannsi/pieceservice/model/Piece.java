package com.bannsi.pieceservice.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.bannsi.pieceservice.model.Keyword;
import com.bannsi.pieceservice.model.WhoKeyword;

import org.springframework.data.annotation.CreatedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="pieces")
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="piece_id", nullable = false)    
    private Long pieceId;
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
    
    
    public Piece withUserId(String userId){
        this.setUserId(userId);
        return this;
    }
    
    public Piece withTitle(String title){
        this.setTitle(title);
        return this;
    }
    
    public Piece withDate(Date date){
        this.setDate(date);
        return this;
    }

    public Piece withContent(String content){
        this.setContent(content);
        return this;
    }

    public Piece withLatitude(Double latitude){
        this.setLatitude(latitude);
        return this;
    }

    public Piece withLongitude(Double longitude){
        this.setLongitude(longitude);
        return this;
    }

    public Piece withAddress(String address){
        this.setAddress(address);
        return this;
    }

    public Piece withAddressDetail(String addressDetail){
        this.setAddressDetail(addressDetail);
        return this;
    }

    public Piece withPlaceUrl(String placeUrl){
        this.placeUrl = placeUrl;
        return this;
    }

    public Piece withCreatedAt(Date createdAt){
        this.setCreatedAt(createdAt);
        return this;   
    }
}
