package com.java.project.unit.paragon;

import com.java.project.paragon.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ParagonServiceImplTest {

    @Mock
    private ParagonRepository paragonRepository;

    @InjectMocks
    private ParagonServiceImpl paragonService;

    @Test
    void getAllParagones_ShouldReturnListOfParagonDTOs() {
        List<Paragon> mockParagons = List.of(
                new Paragon(1, "Lublin", "21-372", 1, 1, 100.0),
                new Paragon(2, "Szczebrzeszyn", "69-246", 2, 2, 200.0)
        );

        Mockito.when(paragonRepository.findAll()).thenReturn(mockParagons);

        List<ParagonDTO> result = paragonService.getAllParagones();

        assertEquals(2, result.size());
        assertEquals("Lublin", result.get(0).getMiasto());
        assertEquals("Szczebrzeszyn", result.get(1).getMiasto());
    }

    @Test
    void getParagonById_ShouldReturnParagonDTO() {
        Paragon mockParagon = new Paragon(1, "Lublin", "21-372", 1, 1, 100.0);

        Mockito.when(paragonRepository.findById(1)).thenReturn(Optional.of(mockParagon));

        ParagonDTO result = paragonService.getParagonById(1);

        assertEquals("Lublin", result.getMiasto());
        assertEquals("21-372", result.getKodPocztowy());
    }

    @Test
    void insertParagon_ShouldSaveAndReturnParagon() {
        ParagonPOJO inputParagon = new ParagonPOJO("Lublin", "21-372", 1, 1, 100.0);
        Paragon savedParagon = new Paragon(1, "Lublin", "21-372", 1, 1, 100.0);

        Mockito.when(paragonRepository.saveAndFlush(Mockito.any(Paragon.class))).thenReturn(savedParagon);

        Paragon result = paragonService.insertParagon(inputParagon);

        assertNotNull(result);
        assertEquals("Lublin", result.getMiasto());
        assertEquals(100.0, result.getSuma());
    }

    @Test
    void updateParagon_ShouldUpdateAndReturnParagon() {
        Paragon existingParagon = new Paragon(1, "Lublin", "21-372", 1, 1, 100.0);
        ParagonPOJO updatedParagon = new ParagonPOJO("Szczebrzeszyn", "69-246", 2, 2, 200.0);
        Paragon updatedEntity = new Paragon(1, "Szczebrzeszyn", "69-246", 2, 2, 200.0);

        Mockito.when(paragonRepository.findById(1)).thenReturn(Optional.of(existingParagon));
        Mockito.when(paragonRepository.saveAndFlush(Mockito.any(Paragon.class))).thenReturn(updatedEntity);

        Paragon result = paragonService.updateParagon(updatedParagon, 1);

        assertEquals("Szczebrzeszyn", result.getMiasto());
        assertEquals(200.0, result.getSuma());
    }

    @Test
    void deleteParagon_ShouldDeleteParagon() {
        Paragon existingParagon = new Paragon(1, "Lublin", "21-372", 1, 1, 100.0);

        Mockito.when(paragonRepository.findById(1)).thenReturn(Optional.of(existingParagon));
        Mockito.doNothing().when(paragonRepository).delete(existingParagon);

       assertDoesNotThrow(() -> paragonService.deleteParagon(1));
        Mockito.verify(paragonRepository, Mockito.times(1)).delete(existingParagon);
    }

    @Test
    void deleteParagon_WhenParagonNotFound_ShouldThrowException() {
        Mockito.when(paragonRepository.findById(1)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> paragonService.deleteParagon(1));

        assertEquals("Paragon not found", exception.getMessage());
    }
}
