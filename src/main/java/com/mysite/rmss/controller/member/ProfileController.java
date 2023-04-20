package com.mysite.rmss.controller.member;

import com.mysite.rmss.config.auth.CurrentMember;
import com.mysite.rmss.controller.validator.PasswordEditFormValidator;
import com.mysite.rmss.domain.member.Member;
import com.mysite.rmss.dto.member.MemberInfoResponseDto;
import com.mysite.rmss.dto.member.MemberPasswordEditForm;
import com.mysite.rmss.dto.member.MemberProfileEditForm;
import com.mysite.rmss.dto.shop.ShopInfoResponseDto;
import com.mysite.rmss.service.member.MemberSecurityService;
import com.mysite.rmss.service.member.MemberService;
import com.mysite.rmss.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class ProfileController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    private final PasswordEditFormValidator passwordEditFormValidator;
    private final MemberSecurityService memberSecurityService;
    private final ShopService shopService;

    @InitBinder("passwordEditForm")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(passwordEditFormValidator);
    }

    // profile page
    @GetMapping("/{username}")
    public String profile(@PathVariable("username") String username,
                          @CurrentMember Member currentMember,
                          Model model) {

        MemberInfoResponseDto findMemberDto = memberService.findMemberInfoByUsername(username);

        boolean isMyPage = currentMember != null && currentMember.getUsername().equals(findMemberDto.getUsername());
        boolean existsShop = false;
        ShopInfoResponseDto shopInfoByUsername = shopService.getShopInfoByUsername(findMemberDto.getUsername());
        if (shopInfoByUsername != null) {
            existsShop = true;
        }

        model.addAttribute("existsShop", existsShop);
        model.addAttribute("shopInfo", shopInfoByUsername);
        model.addAttribute("member", findMemberDto);
        model.addAttribute("isMyPage", isMyPage);

        return "members/profile";
    }

    @GetMapping("/settings")
    public String editForm(@ModelAttribute("profileEditForm") MemberProfileEditForm form,
                           @CurrentMember Member currentMember,
                           Model model) {

        if (currentMember == null) {
            // 로그인 멤버 없음
            return "redirect:/";
        }

        MemberInfoResponseDto dto = memberService.findById(currentMember.getId());
        form.mappingProfileInfo(dto);
        model.addAttribute("member", currentMember);

        return "members/profileEdit";
    }

    @PostMapping("/settings")
    public String edit(@Valid @ModelAttribute("profileEditForm") MemberProfileEditForm form,
                       BindingResult bindingResult,
                       @CurrentMember Member currentMember,
                       RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "members/profileEdit";
        }

        memberService.updateProfile(currentMember.getId(), form);

        redirectAttributes.addFlashAttribute("message", "프로필을 수정하였습니다.");
        return "redirect:/profile/settings";
    }


    // ===== password edit ===== //
    @GetMapping("/password/edit")
    public String passwordEditForm(@ModelAttribute("passwordEditForm") MemberPasswordEditForm passwordEditForm) {
        return "members/passwordEdit";
    }

    @PostMapping("/password/edit")
    public String passwordEdit(@Valid @ModelAttribute("passwordEditForm") MemberPasswordEditForm passwordEditForm,
                               BindingResult bindingResult,
                               @CurrentMember Member currentMember,
                               RedirectAttributes redirectAttributes) {

        if (!passwordEncoder.matches(passwordEditForm.getPassword(), currentMember.getPassword())) {
            bindingResult.rejectValue("password", "passwordIncorrect",
                    "기존 비밀번호를 잘못 입력하셨습니다. 다시 입력해 주세요.");
        }

        if (bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "members/passwordEdit";
        }

        memberService.passwordEdit(currentMember.getId(), passwordEditForm.getNewPassword1());

        renewAuthenticationAfterPasswordEdit(currentMember, passwordEditForm);

        redirectAttributes.addFlashAttribute("message", "비밀번호가 변경되었습니다.");
        return "redirect:/profile/password/edit";
    }

    private void renewAuthenticationAfterPasswordEdit(Member currentMember,
                                                      MemberPasswordEditForm passwordEditForm) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails newPrincipal = memberSecurityService.loadUserByUsername(currentMember.getUsername());
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, passwordEditForm.getNewPassword1(), newPrincipal.getAuthorities());
        newAuth.setDetails(authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
