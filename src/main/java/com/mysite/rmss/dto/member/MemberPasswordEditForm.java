package com.mysite.rmss.dto.member;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberPasswordEditForm {

    @NotBlank(message = "필수 항목입니다.")
    private String password;

    @NotBlank(message = "필수 항목입니다.")
    @Length(min = 8, max = 25)
    private String newPassword1;

    @NotBlank(message = "필수 항목입니다.")
    @Length(min = 8, max = 25)
    private String newPassword2;

    public MemberPasswordEditForm(String password, String newPassword1, String newPassword2) {
        this.password = password;
        this.newPassword1 = newPassword1;
        this.newPassword2 = newPassword2;
    }
}
