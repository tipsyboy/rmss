package com.mysite.rmss.controller.validator;

import com.mysite.rmss.dto.member.MemberPasswordEditForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@RequiredArgsConstructor
@Component
public class PasswordEditFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        log.info(" ===== check the validator class =====");
        return MemberPasswordEditForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.info(" ===== check the validator logic =====");
        MemberPasswordEditForm form = (MemberPasswordEditForm) target;

        log.info("password={}", form.getPassword());
        log.info("password1={}", form.getNewPassword1());
        log.info("password2={}", form.getNewPassword2());

        if (!form.getNewPassword1().equals(form.getNewPassword2())) {
            errors.rejectValue("newPassword2", "passwordNotEquals",
                    "비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
        }
    }
}
