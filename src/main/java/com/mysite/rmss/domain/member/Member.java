package com.mysite.rmss.domain.member;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String username; // 로그인 아이디

    @Column(unique = true)
    private String email; // 이메일

    private String password; // 비밀번호

    private String bio; // 한 줄 소개

    // TODO: Embedded 타입으로 이후에 Address 를 추가할 것


    // ===== 생성 ===== //
    protected Member() {}

    @Builder
    public Member(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }


    // ===== update ===== //
    public void updateProfile(String bio) {
        this.bio = bio;
    }
}
