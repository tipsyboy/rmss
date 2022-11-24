package com.mysite.rmss.dto.member;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberSaveForm {

    @NotBlank(message = "공백을 허용하지 않습니다.")
    @Length(min = 3, max = 15)
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,15}$")
    private String username;

    @NotBlank
    @Length(min = 8, max = 25)
    private String password1;

    @NotBlank
    @Length(min = 8, max = 25)
    private String password2;

    @NotBlank
    @Email
    private String email;

    public MemberSaveForm(String username, String password1, String password2, String email) {
        this.username = username;
        this.password1 = password1;
        this.password2 = password2;
        this.email = email;
    }
}
