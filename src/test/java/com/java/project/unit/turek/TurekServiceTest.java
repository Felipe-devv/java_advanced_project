package com.java.project.unit.turek;

import com.java.project.turek.Turek;
import com.java.project.turek.TurekDTO;
import com.java.project.turek.TurekRepository;
import com.java.project.turek.TurekServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class TurekServiceTest {

    @Mock
    private TurekRepository turekRepository;

    @InjectMocks
    private TurekServiceImpl turekService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTureks() {
        // given
        Turek turek1 = new Turek(1, "Jan", "Kowalski", "Developer", 25.0, 1);
        Turek turek2 = new Turek(2, "Anna", "Nowak", "Manager", 30.0, 1);
        when(turekRepository.findByStatusNot(0)).thenReturn(Arrays.asList(turek1, turek2));

        // when
        List<TurekDTO> tureks = turekService.getAllTureks();

        // then
        assertEquals(tureks.size(), 2);
        assertEquals(tureks.get(0).getImie(), "Jan");
        assertEquals(tureks.get(1).getImie(), "Anna");
    }
    @Test
    void testGetTurekById() {
        // given
        Turek turek = new Turek(1, "Jan", "Kowalski", "Developer", 25.0, 1);
        when(turekRepository.findById(1)).thenReturn(Optional.of(turek));

        // when
        TurekDTO result = turekService.getTurekById(1);

        // then
        assertEquals(result.getImie(), "Jan");
        assertEquals(result.getNazwisko(), "Kowalski");
    }

    @Test
    void testGetTurekByIdInactive() {
        // given
        Turek turek = new Turek(1, "Jan", "Kowalski", "Developer", 25.0, 0);
        when(turekRepository.findById(1)).thenReturn(Optional.of(turek));

        // when & then
        try {
            turekService.getTurekById(1);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Turek is inactive");
        }
    }

    @Test
    void testGetTurekByIdNotFound() {
        // given
        when(turekRepository.findById(1)).thenReturn(Optional.empty());

        // when & then
        try {
            turekService.getTurekById(1);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Turek not found");
        }
    }

    @Test
    void testInsertTurek() {
        // given
        Turek newTurek = new Turek(1, "Jan", "Kowalski", "Developer", 25.0, 1);

        // when
        turekService.insertTurek(newTurek);

        // then
        ArgumentCaptor<Turek> captor = ArgumentCaptor.forClass(Turek.class);
        verify(turekRepository).save(captor.capture());
        Turek capturedTurek = captor.getValue();

        assertEquals(capturedTurek.getImie(), "Jan");
        assertEquals(capturedTurek.getNazwisko(),"Kowalski");
        assertEquals(capturedTurek.getStanowisko(), "Developer");
    }

    @Test
    void testUpdateTurekInactive() {
        // given
        Turek existingTurek = new Turek(1, "Jan", "Kowalski", "Developer", 25.0, 0);
        Turek updatedTurek = new Turek(1, "Jan", "Kowalski", "Manager", 30.0, 0);
        when(turekRepository.findById(1)).thenReturn(Optional.of(existingTurek));

        // when & then
        try {
            turekService.updateTurek(updatedTurek, 1);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Turek is inactive");
        }
    }


    @Test
    void testDeactivateTurekNotFound() {
        // given
        when(turekRepository.findById(1)).thenReturn(Optional.empty());

        // when & then
        try {
            turekService.deactivateTurek(1);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Turek not found");
        }
    }

    @Test
    void testDeleteTurek() {
        // given
        Turek turek = new Turek(1, "Jan", "Kowalski", "Developer", 25.0, 1);
        when(turekRepository.findById(1)).thenReturn(Optional.of(turek));

        // when
        turekService.deleteTurek(1);

        // then
        verify(turekRepository).deleteById(1);
    }

    @Test
    void testDeleteTurekNotFound() {
        // given
        when(turekRepository.findById(1)).thenReturn(Optional.empty());

        // when & then
        try {
            turekService.deleteTurek(1);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(),"Turek not found");
        }
    }
}