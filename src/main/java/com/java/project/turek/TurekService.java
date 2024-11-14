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
        var turekList = turekRepository.findByStatusNot(0);
        return turekList.stream().map(TurekDTO::new).toList();
    }

    public TurekDTO getTurekById(int id) {
        Turek turek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
        if (turek.getStatus() == 0) {
            throw new RuntimeException("Turek is inactive");
        }
        return new TurekDTO(turek);
    }

    public Turek insertTurek(Turek turek) {
        return turekRepository.save(turek);
    }

    public Turek updateTurek(Turek turek, int id) {
    Turek existingTurek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
    existingTurek.setImie(turek.getImie());
    existingTurek.setNazwisko(turek.getNazwisko());
    existingTurek.setStanowisko(turek.getStanowisko());
    existingTurek.setStawkaGodzinowa(turek.getStawkaGodzinowa());

        if (turek.getStatus() == 0) {
            throw new RuntimeException("Turek is inactive");
        }

    return turekRepository.save(existingTurek);
}

    public void deleteTurek(int id) {
    turekRepository.deleteById(id);
}

    public Turek deactivateTurek(int id) {
    Turek turek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
    turek.setStatus(0);
    return turekRepository.save(turek);
}


}
