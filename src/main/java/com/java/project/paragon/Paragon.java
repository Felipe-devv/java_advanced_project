package com.java.project.paragon;

import com.java.project.kebab.Kebab;
import com.java.project.turek.Turek;
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
    private Integer turek;
    private Integer kebab;
    private Integer podatek;
    private Double suma ;

    public Paragon(String miasto, String kodPocztowy, Integer kebab,Integer turek, Double suma) {
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.kebab = kebab;
        this.turek = turek;
        this.suma = suma;
    }

}
