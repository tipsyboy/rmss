package com.mysite.rmss.config.auth.dto;

import com.mysite.rmss.domain.member.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Getter
public class CurrentMemberDto extends User {

    private Member member;

    public CurrentMemberDto(Member member, List<GrantedAuthority> authorities) {
        super(member.getUsername(), member.getPassword(), authorities);
        this.member = member;
    }

}
