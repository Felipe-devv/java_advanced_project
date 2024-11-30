package com.java.project.unit.turek;


import com.java.project.turek.Turek;
import com.java.project.turek.TurekRepository;
import com.java.project.turek.TurekService;
import com.java.project.turek.TurekServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


public class TurekServiceTest {

    @Test
    void testInsertTurek() {
        // Przygotowanie danych
        TurekRepository turekRepository = mock(TurekRepository.class); // Mockowanie repozytorium
        TurekService turekService = new TurekServiceImpl(turekRepository);

        Turek newTurek = new Turek(1, "Jan", "Kowalski", "Developer", 50.0, 1);

        // Ustawienie mocka: co powinno zwrócić repozytorium, gdy wywołamy save
        when(turekRepository.save(newTurek)).thenReturn(newTurek);

        // Akcja: wywołanie metody insertTurek
        Turek result = turekService.insertTurek(newTurek);

        // Sprawdzenie wyników: upewniamy się, że wynik jest taki, jak oczekiwano
        assertNotNull(result);
        assertEquals("Jan", result.getImie());
        assertEquals("Kowalski", result.getNazwisko());

        // Weryfikujemy, że metoda save została wywołana dokładnie raz
        verify(turekRepository, times(1)).save(newTurek);
    }
}