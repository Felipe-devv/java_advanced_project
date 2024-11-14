package com.java.project.turek;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurekRepository extends JpaRepository<Turek, Long> {
}



//
//public List<Turek> getAllTureks() {
//    return turekRepository.findAll();
//}
//
//public Turek getTurekById(Long id) {
//    return turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
//}
//
//public Turek insertTurek(Turek turek) {
//    return turekRepository.save(turek);
//}
//
//public Turek updateTurek(Turek turek, Long id) {
//    Turek existingTurek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
//    existingTurek.setImie(turek.getImie());
//    existingTurek.setNazwisko(turek.getNazwisko());
//    existingTurek.setStanowisko(turek.getStanowisko());
//    existingTurek.setStawkaGodzinowa(turek.getStawkaGodzinowa());
//
//    existingTurek.setStatus(turek.getStatus());
//    return turekRepository.save(existingTurek);
//}
//
//public void deleteTurek(Long id) {
//    turekRepository.deleteById(id);
//}
//
//public Turek deactivateTurek(Long id) {
//    Turek turek = turekRepository.findById(id).orElseThrow(() -> new RuntimeException("Turek not found"));
//    turek.setStatus(0);
//    return turekRepository.save(turek);
//}