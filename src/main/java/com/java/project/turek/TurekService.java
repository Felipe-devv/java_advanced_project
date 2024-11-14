package com.java.project.turek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TurekService {

    @Autowired
    private TurekRepository turekRepository;

    public List<TurekDTO> getAllTureks() {
        var turekList = turekRepository.findAll();
        return turekList.stream().map(TurekDTO::new).toList();
    }

    public TurekDTO getTurekById(Long id) {
        return new TurekDTO(turekRepository.findById(id).orElseThrow(()->new RuntimeException("Turek not found")));
    }

    public Turek insertTurek(Turek turek) {
        return turekRepository.save(turek);
    }

    public Turek updateTurek(Turek turek, Long id) {
    Turek existingTurek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
    existingTurek.setImie(turek.getImie());
    existingTurek.setNazwisko(turek.getNazwisko());
    existingTurek.setStanowisko(turek.getStanowisko());
    existingTurek.setStawkaGodzinowa(turek.getStawkaGodzinowa());

    existingTurek.setStatus(turek.getStatus());
    return turekRepository.save(existingTurek);
}

    public void deleteTurek(Long id) {
    turekRepository.deleteById(id);
}

    public Turek deactivateTurek(Long id) {
    Turek turek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
    turek.setStatus(0);
    return turekRepository.save(turek);
}


}
