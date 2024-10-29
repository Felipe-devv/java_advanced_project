package com.java.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paragon {

    private Integer id;
    private String miasto;
    private String kodPocztowy;
    private List<Kebab> kebaby;
    private Double suma ;

    public Paragon(String miasto, String kodPocztowy, List<Kebab> kebaby, Double suma) {
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.kebaby = kebaby;
        this.suma = suma;
    }

    public void addKebab(Kebab kebab) {
        if(kebaby.isEmpty())
        {
            kebaby = new ArrayList<>();
        }
        kebaby.add(kebab);
    }
}
