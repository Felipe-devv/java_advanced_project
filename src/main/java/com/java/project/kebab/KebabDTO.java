package com.java.project.kebab;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
        Kebab kebab = new Kebab();
        kebab.setId(this.id);
        kebab.setName(this.name);
        kebab.setSize(this.size);
        kebab.setMeat(this.meat);
        kebab.setSauce(this.sauce);
        kebab.setPrice(this.price);
        return kebab;
    }
}
