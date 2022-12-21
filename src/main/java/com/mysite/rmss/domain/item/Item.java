package com.mysite.rmss.domain.item;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;
    private String description;
    private Integer price;
    private Integer stock;
    private LocalDateTime createDate;

    // ===== 생성 ===== //
    protected Item() {}

}