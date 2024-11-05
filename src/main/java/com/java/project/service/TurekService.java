package com.java.project.service;

import com.java.project.model.turek.Turek;

import java.util.ArrayList;
import java.util.List;

public class TurekService {

    public static List<Turek> getTurekList(){
        List<Turek> turkowie = new ArrayList<>();

        //Watch this for all cost: https://www.youtube.com/watch?v=O08XTXJSOtI&ab_channel=KraKenn

        Turek turek1 = Turek.builder()
                .id(1L)
                .imie("Ya'qub Qamar")
                .nazwisko("Ad-Din Dibiazah")
                .stanowisko("Kucharz")
                .stawkaGodzinowa(22.50)
                .build();
        Turek turek2 = Turek.builder()
                .id(2L)
                .imie("Khalid")
                .nazwisko("Kashmiri")
                .stanowisko("Kasjer")
                .stawkaGodzinowa(22.50)
                .build();
        Turek turek3 = Turek.builder()
                .id(3L)
                .imie("Khidir")
                .nazwisko("Karawita")
                .stanowisko("Dostawca")
                .stawkaGodzinowa(22.50)
                .build();

        turkowie.add(turek1);
        turkowie.add(turek2);
        turkowie.add(turek3);

        return turkowie;
    }


}
