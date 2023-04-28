package com.mysite.rmss.dto.member;

import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.file.UploadFile;
import lombok.Getter;

@Getter
public class MemberInfoResponseDto {

    private Long memberId;
    private String username;
    private String email;
    private String bio;
    private UploadFile memberProfileImage;

    public MemberInfoResponseDto(Member entity) {
        this.memberId = entity.getId();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.bio = entity.getBio();
        this.memberProfileImage = entity.getMemberProfileImage();
    }
}
