package com.java.project.kebab.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KebabDTO {
    private Integer id;
    private String name;
    private String size;
    private String meat;
    private String sauce;
    private double price;

    public KebabDTO(Kebab kebab) {
        this.id = kebab.getId();
        this.name = kebab.getName();
        this.size = kebab.getSize();
        this.meat = kebab.getMeat();
        this.sauce = kebab.getSauce();
        this.price = kebab.getPrice();
    }

    public Kebab toEntity() {
        return Kebab.builder()
                .id(this.id)
                .name(this.name)
                .size(this.size)
                .meat(this.meat)
                .sauce(this.sauce)
                .price(this.price)
                .build();
    }
}
