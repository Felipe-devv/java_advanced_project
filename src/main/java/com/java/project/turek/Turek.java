package com.java.project.turek;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Turek")

public class Turek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "imie")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;
    @Column(name = "stanowisko")
    private String stanowisko;
    @Column(name = "stawka_godzinowa")
    private Double stawkaGodzinowa;
    @Column(name = "status")
    private int status;
//    private Boolean active;
}
