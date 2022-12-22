package com.mysite.rmss.controller.item;

import com.mysite.rmss.dto.item.ItemCreateForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ItemController {

    @GetMapping("/{shopName}/items/add")
    public String createItemForm(@PathVariable String shopName,
                                 @ModelAttribute ItemCreateForm itemCreateForm) {

        return "item/createItemForm";
    }

}
