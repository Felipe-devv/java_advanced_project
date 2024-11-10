package com.java.project.paragon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParagonController {

    @Autowired
    private ParagonService paragonService;

    @GetMapping("/paragones")
    public List<ParagonDTO> getParagons() {
        return paragonService.getAllParagones();
    }

    @GetMapping("/paragones/{paragonId}")
    public ParagonDTO getParagonById(@PathVariable int paragonId) {
        return paragonService.getParagonById(paragonId);
    }

    @PostMapping("/paragones")
    public String createParagon(@RequestBody ParagonDTO paragon) {

        if(paragonService.insertParagon(paragon))
        {
            return "Paragon dodany";
        }
        return "Nie udało się dodać paragonu";
    }

    @PutMapping("/paragones")
    public String updateParagon( @RequestBody ParagonDTO paragon) {

        if(paragonService.updateParagon(paragon)){return "Paragon zaaktualizowany";}
        return "Nie udało się zaaktualizować paragonu";
    }

    @DeleteMapping("/paragones/{paragonId}")
    public String deleteParagon(@PathVariable int paragonId) {

        if(paragonService.deleteParagon(paragonId)){return "Paragon usunięty";}
        return "Nie udało się usunąć paragonu";
    }

}
