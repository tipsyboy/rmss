package com.mysite.rmss.dto.member;

import lombok.Getter;

@Getter
public class MemberProfileEditForm {

    private String username;
    private String bio;

    public MemberProfileEditForm(String username, String bio) {
        this.username = username;
        this.bio = bio;
    }

    public void mappingProfileInfo(MemberInfoResponseDto infoResponseDto) {
        this.username = infoResponseDto.getUsername();
        this.bio = infoResponseDto.getBio();
    }
}
