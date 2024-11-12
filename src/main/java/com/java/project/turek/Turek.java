package com.java.project.turek;

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
//    private Boolean active;
}
