package com.java.project.paragon;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paragones")
@PreAuthorize("hasAnyRole('szef','regular')")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParagonController {


    private final ParagonService paragonService;

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

        try{
            paragonService.insertParagon(paragon);
            return "Paragon dodany";
        }
         catch(Exception e){
             return "Nie udało się dodać paragonu";
         }
    }

    @PutMapping("/{paragonId}")
    public String updateParagon( @RequestBody ParagonPOJO paragon,@PathVariable int paragonId) {

       try{return "Paragon zaaktualizowany";}
       catch(Exception e) {
           return "Nie udało się zaaktualizować paragonu";
       }
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