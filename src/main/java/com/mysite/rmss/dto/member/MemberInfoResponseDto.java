package com.mysite.rmss.dto.member;

import com.mysite.rmss.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {

    private Long memberId;
    private String username;
    private String email;
    private String bio;

    public MemberInfoResponseDto(Member entity) {
        this.memberId = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.bio = entity.getBio();
    }
}
