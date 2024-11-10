package com.java.project.paragon;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParagonDTO {

    private Integer id;
    private String miasto;
    private String kodPocztowy;
    private Integer kebab;
    private Integer turek;
    private Double suma;

    public ParagonDTO(Paragon paragon) {
        this.id = paragon.getId();
        this.miasto = paragon.getMiasto();
        this.kodPocztowy = paragon.getKodPocztowy();
        this.kebab = paragon.getKebab();
        this.turek = paragon.getTurek();
        this.suma = paragon.getSuma();
    }

    public Paragon toParagonEntity() {
        return Paragon.builder()
                .id(this.id)
                .miasto(this.miasto)
                .kodPocztowy(this.kodPocztowy)
                .kebab(this.kebab)
                .turek(this.turek)
                .suma(this.suma)
                .build();
    }


    @Override
    public String toString() {
        return "ParagonDTO{" +
                "id=" + id +
                ", miasto='" + miasto + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                ", kebab=" + kebab +
                ", suma=" + suma +
                '}';
    }
}
