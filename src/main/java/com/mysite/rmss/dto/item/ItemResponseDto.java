package com.mysite.rmss.dto.item;

import com.mysite.rmss.domain.item.Item;
import lombok.Getter;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@Getter
public class ItemResponseDto {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer stock;
    private String priceWon;
    private LocalDateTime createDate;
    private String description;
    private String imgPath = "https://dummyimage.com/600x700/dee2e6/6c757d.jpg";
//    private Boolean status; // 상태

    public ItemResponseDto(Item entity) {
        this.id = entity.getId();
        this.itemName = entity.getItemName();
        this.price = entity.getPrice();
        this.stock = entity.getStock();
        this.description = entity.getDescription();
        this.createDate = entity.getCreateDate();
        this.priceWon = formatWon(entity.getPrice());
        this.imgPath = entity.getImgPath();
    }

    private String formatWon(Integer price) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(price);
    }
}
