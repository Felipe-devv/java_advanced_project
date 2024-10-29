package com.java.project.service;

import java.util.ArrayList;
import java.util.List;

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
        kebab();
    }

    public void kebab() {

        List<Kebab> kebabs = KebabService.returnListOfKebabsMadeWithBuilder();
        KebabService.outputAllKebabs(kebabs);

        Kebab kebab1 = kebabs.get(0);

        KebabDTO kebabDTO1 = new KebabDTO(kebab1);

        KebabStateRestorer restorer = new KebabStateRestorer(kebabDTO1);
        System.out.println("Changed kebab name to Bula");
        kebab1.setName("Bula");
        System.out.println(kebab1);
        System.out.println("Restoring kebab");
        kebab1 = restorer.restorKebab();
        System.out.println(kebab1);
    }
}
