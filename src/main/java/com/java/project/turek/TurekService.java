package com.java.project.turek;

import java.util.List;

public interface TurekService {
    List<TurekDTO> getAllTureks();

    TurekDTO getTurekById(int id);

    Turek insertTurek(Turek turek);

    Turek updateTurek(Turek turek, int id);

    void deleteTurek(int id);

    Turek deactivateTurek(int id);
}
