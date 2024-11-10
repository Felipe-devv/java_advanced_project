package com.java.project.paragon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paragon {

    private Integer id;
    private String miasto;
    private String kodPocztowy;
//    private Turek turek;
//    private Kebab kebab;
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
