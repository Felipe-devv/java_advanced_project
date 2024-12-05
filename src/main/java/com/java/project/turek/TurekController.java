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
        try {
            turekService.insertTurek(turekDTO.toEntity());
            return "Turek added";
        } catch (Exception e) {
            return "Failed to add Turek: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public String updateTurek(@RequestBody TurekDTO turekDTO, @PathVariable int id) {
        try {
            turekService.updateTurek(turekDTO.toEntity(), id);
            return "Turek updated";
        } catch (Exception e) {
            return "Failed to update Turek: " + e.getMessage();
        }
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
        try {
            turekService.deactivateTurek(id);
            return "Turek updated";
        } catch (Exception e) {
            return "Failed to update Turek: " + e.getMessage();
        }
    }
}
