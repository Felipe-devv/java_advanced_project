package com.java.project.turek;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class TurekDTO {
    private int id;
    private String imie;
    private String nazwisko;
    private String stanowisko;
    private Double stawkaGodzinowa;
    private int status =1;

    // Konstruktor mapujący encję na DTO
    public TurekDTO(Turek turek) {
        this.id = turek.getId();
        this.imie = turek.getImie();
        this.nazwisko = turek.getNazwisko();
        this.stanowisko = turek.getStanowisko();
        this.stawkaGodzinowa = turek.getStawkaGodzinowa();
        this.status = turek.getStatus();
    }


    // Metoda przywracająca encję z DTO
    public Turek toEntity() {
        return Turek.builder()
                .id(id)
                .imie(imie)
                .nazwisko(nazwisko)
                .stanowisko(stanowisko)
                .stawkaGodzinowa(stawkaGodzinowa)
                .status(status)
                .build();
    }
    // snapshot do utworzenia kopii TurekDTO
//    public TurekDTO snapshot() {
//        return new TurekDTO(this.toEntity());
//    }


}
