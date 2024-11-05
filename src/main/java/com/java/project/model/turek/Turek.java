package com.java.project.model.turek;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Turek {
    private Long id;
    private String imie;
    private String nazwisko;
    private String stanowisko;
    private Double stawkaGodzinowa;
}
