package com.java.project.turek;

import lombok.Data;

@Data
public class TurekDTO {
    private Long id;
    private String imie;
    private String nazwisko;
    private String stanowisko;
    private Double stawkaGodzinowa;

    // Konstruktor mapujący encję na DTO
    public TurekDTO(Turek turek) {
        this.id = turek.getId();
        this.imie = turek.getImie();
        this.nazwisko = turek.getNazwisko();
        this.stanowisko = turek.getStanowisko();
        this.stawkaGodzinowa = turek.getStawkaGodzinowa();
    }


    // Metoda przywracająca encję z DTO
    public Turek toEntity() {
        return Turek.builder()
                .id(id)
                .imie(imie)
                .nazwisko(nazwisko)
                .stanowisko(stanowisko)
                .stawkaGodzinowa(stawkaGodzinowa)
                .build();
    }
    // snapshot do utworzenia kopii TurekDTO
    public TurekDTO snapshot() {
        return new TurekDTO(this.toEntity());
    }


}