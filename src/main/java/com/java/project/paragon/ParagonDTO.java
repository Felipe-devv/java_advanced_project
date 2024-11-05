package com.java.project.paragon;

import com.java.project.kebab.Kebab;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParagonDTO {

    private Integer id;
    private String miasto;
    private String kodPocztowy;
    private List<Kebab> kebaby;
    private Double suma;
    private Paragon copy;

    public ParagonDTO(Paragon paragon) {
        this.id = paragon.getId();
        this.miasto = paragon.getMiasto();
        this.kodPocztowy = paragon.getKodPocztowy();
        this.kebaby = paragon.getKebaby();
        this.suma = paragon.getSuma();
        this.copy=paragon;

    }

    public Paragon toParagonEntity() {
        return Paragon.builder()
                .id(this.id)
                .miasto(this.miasto)
                .kodPocztowy(this.kodPocztowy)
                .kebaby(this.kebaby)
                .suma(this.suma)
                .build();
    }


    public void addKebab(Kebab kebab) {
        if (this.kebaby.isEmpty()) {
            kebaby = new ArrayList<>();
        }
        kebaby.add(kebab);
    }

    @Override
    public String toString() {
        return "ParagonDTO{" +
                "id=" + id +
                ", miasto='" + miasto + '\'' +
                ", kodPocztowy='" + kodPocztowy + '\'' +
                ", kebaby=" + kebaby +
                ", suma=" + suma +
                '}';
    }
}
