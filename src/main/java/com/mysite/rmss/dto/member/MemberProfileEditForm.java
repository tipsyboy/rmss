package com.mysite.rmss.dto.member;

import lombok.Getter;

@Getter
public class MemberProfileEditForm {

    private String bio;

    public MemberProfileEditForm(String bio) {
        this.bio = bio;
    }

    public void mappingProfileInfo(MemberInfoResponseDto infoResponseDto) {
        this.bio = infoResponseDto.getBio();
    }
}
