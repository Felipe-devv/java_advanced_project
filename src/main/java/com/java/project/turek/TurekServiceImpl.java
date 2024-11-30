package com.java.project.turek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurekServiceImpl implements TurekService {

    private final TurekRepository turekRepository;

    @Autowired
    public TurekServiceImpl(TurekRepository turekRepository) {
        this.turekRepository = turekRepository;
    }

    @Override
    public List<TurekDTO> getAllTureks() {
        var turekList = turekRepository.findByStatusNot(0);
        return turekList.stream().map(TurekDTO::new).toList();
    }

    @Override
    public TurekDTO getTurekById(int id) {
        Turek turek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
        if (turek.getStatus() == 0) {
            throw new RuntimeException("Turek is inactive");
        }
        return new TurekDTO(turek);
    }

    @Override
    public Turek insertTurek(Turek turek) {
        return turekRepository.save(turek);
    }

    @Override
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

    @Override
    public void deleteTurek(int id) {
        turekRepository.deleteById(id);
    }

    @Override
    public Turek deactivateTurek(int id) {
        Turek turek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
        turek.setStatus(0);
        return turekRepository.save(turek);
    }
}
