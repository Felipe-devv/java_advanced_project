package com.java.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.java.project.model.kebab.Kebab;

@Service
public class KebabService {

    public static List<Kebab> returnListOfKebabsMadeWithBuilder(){
        List<Kebab> kebabs = new ArrayList<>();
        
        Kebab kebab1 = Kebab.builder()
                .id(1)
                .name("Pita")
                .size("XL")
                .meat("Chicken")
                .sauce("Kurczak")
                .price(22.99)
                .build();
        Kebab kebab2 = Kebab.builder()
                .id(2)
                .name("Falafel")
                .size("Duży")
                .meat("Kotleciki z ciecierzycy")
                .sauce("Ostry")
                .price(20.99)
                .build();
        
        kebabs.add(kebab1);
        kebabs.add(kebab2);
        
        return kebabs;
    }

    public static void outputAllKebabs(List<Kebab> kebabs) {
        for(Kebab kebab : kebabs) {
            System.out.println(kebab);
        }
    }
}
