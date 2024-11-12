package com.java.project.turek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turek")
public class TurekController {

    @Autowired
    private TurekService turekService;

    @GetMapping("/all")
    public List<TurekDTO> getAllTureks() {
        return turekService.getAllTureks();
    }

    @GetMapping("/{id}")
    public TurekDTO getTurekById(@PathVariable Long id) {
        return turekService.getTurekById(id);
    }

    @PostMapping("/add")
    public String createTurek(@RequestBody TurekDTO turekDTO) {
        if (turekService.insertTurek(turekDTO.toEntity())) {
            return "Turek added";
        }
        return "Failed to add Turek";
    }

    @PutMapping("/{id}")
    public String updateTurek(@RequestBody TurekDTO turekDTO, @PathVariable Long id) {
        if (turekService.updateTurek(turekDTO.toEntity(), id)) {
            return "Turek updated";
        }
        return "Failed to update Turek";
    }

    @DeleteMapping("/{id}")
    public String deleteTurek(@PathVariable Long id) {
        if (turekService.deleteTurek(id)) {
            return "Turek deleted";
        }
        return "Failed to delete Turek";
    }

    @PutMapping("/deactivate/{id}")
    public boolean deactivateTurek(@PathVariable Long id) {
        return turekService.deactivateTurek(id);
    }
//    @PutMapping("/deactivate/{id}")
//    public String deactivateTurek(@PathVariable Long id) {
//        if (turekService.deactivateTurek(id)) {
//            return "Turek deactivated";
//        }
//        return "Failed to deactivate Turek";
//    }
}
