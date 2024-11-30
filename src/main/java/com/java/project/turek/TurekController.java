package com.java.project.turek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turek")
@PreAuthorize("hasRole('szef')")
public class TurekController {

    @Autowired
    private TurekService turekService;

    @GetMapping("/all")
    public List<TurekDTO> getAllTureks() {
        return turekService.getAllTureks();
    }

    @GetMapping("/{id}")
    public TurekDTO getTurekById(@PathVariable int id) {
        return turekService.getTurekById(id);
    }

    @PostMapping("/add")
    public String createTurek(@RequestBody TurekDTO turekDTO) {
        if (turekService.insertTurek(turekDTO.toEntity())!=null) {
            return "Turek added";
        }
        return "Failed to add Turek";
    }

    @PutMapping("/{id}")
    public String updateTurek(@RequestBody TurekDTO turekDTO, @PathVariable int id) {
        if (turekService.updateTurek(turekDTO.toEntity(), id) != null) {
            return "Turek updated";
        }
        return "Failed to update Turek";
    }

    @DeleteMapping("/{id}")
    public String deleteTurek(@PathVariable int id) {

        try {
            turekService.deleteTurek(id);
            return "Turek został usunięty";
        } catch (Exception e) {
            return "Nie udało się usunąć Turka";
        }

    }

    @PutMapping("/deactivate/{id}")
    public String deactivateTurek(@PathVariable int id) {
        if (turekService.deactivateTurek(id) != null) {
            return "Turek updated";
        }
        return "Failed to update Turek";
    }

}
