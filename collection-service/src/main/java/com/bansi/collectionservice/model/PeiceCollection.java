package com.bansi.collectionservice.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "peice_collections")
public class PeiceCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "collection_id", nullable = false)
    private Long collectionId;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "title")
    private String title;
    @OneToMany
    List<Item> items;
}