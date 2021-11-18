package com.bannsi.peiceservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name="peice_likes")
public class PeiceLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="peice_like_id", nullable = false)
    private Long peiceLikeId;
    @Column(name = "liker_id", nullable = false)
    private Long likerId;
    @Column(name = "peice_id", nullable = false)
    private Long peiceId;
}