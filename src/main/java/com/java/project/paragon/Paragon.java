package com.java.project.paragon;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Paragon")
public class Paragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "miasto")
    private String miasto;

    @Column(name = "kod_pocztowy")
    private String kodPocztowy;

    @Column(name = "Turek_id")
    private Long turek;

    @Column(name = "Kebab_id")
    private Integer kebab;

    @Column(name = "suma")
    private Double suma ;

    public Paragon(String miasto, String kodPocztowy, Integer kebab,Long turek, Double suma) {
        this.miasto = miasto;
        this.kodPocztowy = kodPocztowy;
        this.kebab = kebab;
        this.turek = turek;
        this.suma = suma;
    }

}
