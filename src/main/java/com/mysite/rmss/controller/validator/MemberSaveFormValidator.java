package com.mysite.rmss.controller.validator;

import com.mysite.rmss.dto.member.MemberSaveForm;
import com.mysite.rmss.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberSaveFormValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        // 검증 하려는 클래스와 매개변수 clazz 가 같은 타입인지 또는 자식클래스인지 검증
        return MemberSaveForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MemberSaveForm form = (MemberSaveForm) target;

        if (!form.getPassword1().equals(form.getPassword2())) {
            // TODO: errors.properties 정의하기?
            errors.rejectValue("password2", "passwordNotEquals",
                    "비밀번호가 일치하지 않습니다. 비밀번호를 확인해주세요.");
        }

        if (memberRepository.existsByUsername(form.getUsername())) {
            errors.rejectValue("username", "invalidUsername",
                    "이미 사용중인 아이디입니다.");
        }
        if (memberRepository.existsByEmail(form.getEmail())) {
            errors.rejectValue("email", "invalidEmail",
                    "이미 사용중인 이메일입니다.");
        }
    }
}
