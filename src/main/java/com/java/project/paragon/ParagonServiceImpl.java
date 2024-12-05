package com.java.project.paragon;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParagonServiceImpl implements  ParagonService{

    private final ParagonRepository paragonRepository;


    public List<ParagonDTO> getAllParagones() {

        var paragonList = paragonRepository.findAll();

        return paragonList.stream().map(ParagonDTO::new).toList();
    }

    public ParagonDTO getParagonById(int paragonId) {

        return new ParagonDTO(paragonRepository.findById(paragonId).orElseThrow(()->new RuntimeException("Paragon not found")));
    }

    public Paragon insertParagon(ParagonPOJO paragon){

            return paragonRepository.saveAndFlush(
                    Paragon.builder()
                            .kebab(paragon.getKebab())
                            .turek(paragon.getTurek())
                            .suma(paragon.getSuma())
                            .kodPocztowy(paragon.getKodPocztowy())
                            .miasto(paragon.getMiasto())
                            .build());
    }

    public Paragon updateParagon(ParagonPOJO paragon,int paragonId) {
        Paragon existingParagon = paragonRepository.findById(paragonId).orElseThrow(()->new RuntimeException("Paragon not found"));
        existingParagon.setKebab(paragon.getKebab());
        existingParagon.setTurek(paragon.getTurek());
        existingParagon.setSuma(paragon.getSuma());
        existingParagon.setMiasto(paragon.getMiasto());
        existingParagon.setKodPocztowy(paragon.getKodPocztowy());
        return paragonRepository.saveAndFlush(existingParagon);
    }

    public void deleteParagon(int paragonId) {
        paragonRepository.delete(paragonRepository.findById(paragonId).orElseThrow(()->new RuntimeException("Paragon not found")));
    }

}
