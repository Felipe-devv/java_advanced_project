package com.java.project.service;


import com.java.project.model.kebab.Kebab;
import com.java.project.model.paragon.Paragon;
import com.java.project.model.paragon.ParagonDTO;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ParagonService {

    public static List<Paragon> getParagonsList() {

        List<Kebab> kebabList = KebabService.returnListOfKebabsMadeWithBuilder();
        Paragon paragon = Paragon.builder()
                .id(1)
                .miasto("Lublin")
                .kodPocztowy("20-200")
                .kebaby(kebabList)
                .suma(Paragon.calcSuma(kebabList))
                .build();

        kebabList.add(Kebab.builder()
                .id(3)
                .name("Lawasz")
                .size("XL")
                .meat("Mieszane")
                .sauce("Super ostry")
                .price(27.99)
                .build());

        Paragon paragon1 = Paragon.builder()
                .id(2)
                .miasto("Wrocałw")
                .kodPocztowy("28-700")
                .kebaby(kebabList)
                .suma(Paragon.calcSuma(kebabList))
                .build();

        return List.of(paragon, paragon1);
    }
    public static List<ParagonDTO> convertToDTO(List<Paragon> paragons)
    {
        List<ParagonDTO> paragonDTOList = new ArrayList<>();
        for (Paragon paragon : paragons) {
            ParagonDTO dto = new ParagonDTO(paragon);
//            dto.setCopy(paragon);
            paragonDTOList.add(dto);
        }
        return paragonDTOList;
    }

}
