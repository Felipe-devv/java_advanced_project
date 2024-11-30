package com.java.project.paragon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paragones")
@PreAuthorize("hasAnyRole('szef','regular')")
public class ParagonController {

    @Autowired
    private ParagonService paragonService;

    @GetMapping("/all")
    public List<ParagonDTO> getParagons() {

        return paragonService.getAllParagones();
    }

    @GetMapping("/{paragonId}")
    public ParagonDTO getParagonById(@PathVariable int paragonId) {
        return paragonService.getParagonById(paragonId);
    }

    @PostMapping("")
    public String createParagon(@RequestBody ParagonPOJO paragon) {

        if(paragonService.insertParagon(paragon)!=null)
        {
            return "Paragon dodany";
        }
        return "Nie udało się dodać paragonu";
    }

    @PutMapping("/{paragonId}")
    public String updateParagon( @RequestBody ParagonPOJO paragon,@PathVariable int paragonId) {

        if(paragonService.updateParagon(paragon,paragonId)!=null){return "Paragon zaaktualizowany";}
        return "Nie udało się zaaktualizować paragonu";
    }

    @DeleteMapping("/{paragonId}")
    public String deleteParagon(@PathVariable int paragonId) {

        try {
            paragonService.deleteParagon(paragonId);
            return "Paragon usunięty";
        } catch (Exception e) {
        return "Nie udało się usunąć paragonu";
        }
    }

}