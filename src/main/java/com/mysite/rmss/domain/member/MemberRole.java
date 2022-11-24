package com.mysite.rmss.domain.member;

import lombok.Getter;

@Getter
public enum MemberRole {

    // 게스트 role 이 필요한가?
    GUEST("ROLE_GUEST"), MEMBER("ROLE_MEMBER"), ADMIN("ROLE_ADMIN");

    private final String value;

    MemberRole(String value) {
        this.value = value;
    }
}
