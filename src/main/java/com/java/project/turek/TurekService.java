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
        var turekList = turekRepository.getAllTureks();
        return turekList.stream().map(TurekDTO::new).toList();
    }

    public TurekDTO getTurekById(Long id) {
        return new TurekDTO(turekRepository.getTurekById(id));
    }

    public boolean insertTurek(Turek turek) {
        return turekRepository.insertTurek(turek);
    }

    public boolean updateTurek(Turek turek, Long id) {
        return turekRepository.updateTurek(turek, id);
    }

    public boolean deleteTurek(Long id) {
        return turekRepository.deleteTurek(id);
    }

//    public boolean deactivateTurek(Long id) {
//        return turekRepository.deactivateTurek(id);
//    }


//
//
//    public static List<Turek> getTurekList(){
//        List<Turek> turkowie = new ArrayList<>();
//
//
//
//        //Watch this for all cost: https://www.youtube.com/watch?v=O08XTXJSOtI&ab_channel=KraKenn
//
//        Turek turek1 = Turek.builder()
//                .id(1L)
//                .imie("Ya'qub Qamar")
//                .nazwisko("Ad-Din Dibiazah")
//                .stanowisko("Kucharz")
//                .stawkaGodzinowa(22.50)
//                .build();
//        Turek turek2 = Turek.builder()
//                .id(2L)
//                .imie("Khalid")
//                .nazwisko("Kashmiri")
//                .stanowisko("Kasjer")
//                .stawkaGodzinowa(22.50)
//                .build();
//        Turek turek3 = Turek.builder()
//                .id(3L)
//                .imie("Khidir")
//                .nazwisko("Karawita")
//                .stanowisko("Dostawca")
//                .stawkaGodzinowa(22.50)
//                .build();
//
//        turkowie.add(turek1);
//        turkowie.add(turek2);
//        turkowie.add(turek3);
//
//        return turkowie;
//    }
//
//    private static final Map<Long, TurekDTO> snapshotMap = new HashMap<>(); // Przechowywanie snapshotów
//
//    // Zapisanie stanu do snapshotMap przed dokonaniem modyfikacji
//    public static void modyfikujTurek(Turek turek) {
//        snapshotMap.put(turek.getId(), new TurekDTO(turek).snapshot());
//    }
//
//    // Cofnięcie zmian i przywrócenie obiektu na podstawie snapshotu
//    public static Turek cofnijZmiany(Long turekId) {
//        TurekDTO snapshot = snapshotMap.get(turekId);
//        if (snapshot != null) {
//            return snapshot.toEntity(); // Przywróć stan na podstawie snapshotu
//        }
//        return null; // Jeśli nie ma snapshotu, zwróć null
//    }


}
