package com.bannsi.accountservice.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
@Table(name = "users")
public class User {
    @Id
    @Column(name = "kakao_id", nullable = false)
    private String kakaoId;

    @Column(name = "nickname", nullable = false)
    private String nickname;
    
    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public User withKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public User withNickname(String nickname){
        this.nickname = nickname;
        return this;
    }
}
