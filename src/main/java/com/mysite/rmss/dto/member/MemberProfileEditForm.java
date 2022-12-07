package com.mysite.rmss.dto.member;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class MemberProfileEditForm {

    private String username;

    @Length(max = 30, message = "30자 이내로 입력해주세요.")
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
