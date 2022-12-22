package com.mysite.rmss.controller.item;

import com.mysite.rmss.dto.item.ItemCreateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemController {

    @GetMapping("/{shopPath}/items/add")
    public String createItemForm(@PathVariable String shopPath,
                                 @ModelAttribute ItemCreateForm itemCreateForm,
                                 Model model) {

        model.addAttribute("shopPath", shopPath);
        return "item/createItemForm";
    }

    @PostMapping("/{shopPath}/items/add")
    public String createItem(@PathVariable String shopPath,
                             @Valid @ModelAttribute ItemCreateForm itemCreateForm,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "item/createItemForm";
        }

        // TODO: Item 생성 service 호출
        // TODO: redirect
        return "redirect:/";
    }

}
