package com.java.project.run;

import java.util.List;
import java.util.stream.Collectors;

import com.java.project.model.paragon.Paragon;
import com.java.project.model.paragon.ParagonDTO;
import com.java.project.model.turek.Turek;
import com.java.project.model.turek.TurekDTO;
import com.java.project.service.KebabService;
import com.java.project.service.ParagonService;
import com.java.project.service.TurekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.java.project.model.kebab.Kebab;
import com.java.project.model.kebab.KebabDTO;
import com.java.project.model.kebab.KebabStateRestorer;

public class Lab4 implements CommandLineRunner {

    @Autowired
    private KebabService kebabService;

    @Override
    public void run(String... args) throws Exception {
//        kebab();
        paragon();
        turas();
    }

    public void kebab() {

        List<Kebab> kebabs = KebabService.returnListOfKebabsMadeWithBuilder();
        KebabService.outputAllKebabs(kebabs);

        Kebab kebab1 = kebabs.get(0);

        KebabDTO kebabDTO1 = new KebabDTO(kebab1);

        KebabStateRestorer restorer = new KebabStateRestorer(kebabDTO1);
        System.out.println("Changed kebab name to Bula");
        kebabDTO1.setName("Bula");
        System.out.println(kebab1);
        System.out.println("Restoring kebab");
        kebab1 = restorer.restorKebab();
        System.out.println(kebab1);
    }

    public void paragon()
    {
        List<Paragon> paragons = ParagonService.getParagonsList();
        System.out.println("Paragon list: ");
        paragons.forEach(System.out::println);


        List<ParagonDTO> dtos = ParagonService.convertToDTO(paragons);
        dtos.forEach(dto -> dto.setKodPocztowy("!@#$#%^@&##@"));
        System.out.println("ParagonDTO list with changes");
        dtos.forEach(System.out::println);

        ParagonDTO paragonDTO = dtos.get(1);
        Paragon restored =  paragonDTO.getCopy();
        Paragon saved = paragonDTO.toParagonEntity();

        System.out.println();
        System.out.println("DTO: "+paragonDTO);
        System.out.println("CONVERTED TO ENTITY:"+saved);
        System.out.println("ENTITY RESTORED: "+restored);
    }

    public void turas(){
        List<Turek> turkowie = TurekService.getTurekList();

        Turek turek = turkowie.get(0);
        System.out.println("Oryginalny stan: " + turek);

        // Zapisanie snapshotu przed modyfikacją
        TurekService.modyfikujTurek(turek);

        // Modyfikacja obiektu
        turek.setStanowisko("Manager");
        System.out.println("Po modyfikacji: " + turek);

        // Cofnięcie zmian
        Turek przywroconyTurek = TurekService.cofnijZmiany(turek.getId());
        System.out.println("Przywrócony stan: " + przywroconyTurek);

    }
}
