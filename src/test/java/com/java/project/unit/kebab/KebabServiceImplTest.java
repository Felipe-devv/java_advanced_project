package com.java.project.unit.kebab;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.java.project.kebab.KebabRepository;
import com.java.project.kebab.KebabServiceImpl;
import com.java.project.kebab.domain.Kebab;
import com.java.project.kebab.domain.KebabDTO;
import com.java.project.kebab.payload.AddUpdateKebabRequest;

public class KebabServiceImplTest {

    @Mock
    private KebabRepository kebabRepository;

    @InjectMocks
    private KebabServiceImpl kebabServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*
     * getAllKebabs()
     */
    @Test
    void testGetAllKebabs() {
        // given
        Kebab kebab1 = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, true);
        Kebab kebab2 = new Kebab(2, "Kebab2", "XL", "mieszane", "sos czosnkowy", 20.0, true);
        
        when(kebabRepository.findByIsActiveTrue()).thenReturn(Arrays.asList(kebab1, kebab2));
        // when
        List<KebabDTO> kebabs = kebabServiceImpl.getAllKebabs();

        // then
        assertThat(kebabs.size(), is(2));
        assertThat(kebabs.get(0).getId(), is(1));
        assertThat(kebabs.get(1).getId(), is(2));
    }

    /*
     * getKebabById()
     */

    @Test
    void testGetKebabById() {
        // given
        Kebab kebab = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, true);
        when(kebabRepository.findById(1)).thenReturn(Optional.of(kebab));
        // when
        KebabDTO kebabDTO = kebabServiceImpl.getKebabById(1);

        // then
        assertThat(kebabDTO.getId(), is(1));
    }

    @Test
    void testDoesntGetKebabByIdThatIsInactive() {
        // given
        Kebab kebab = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, false);
        when(kebabRepository.findById(1)).thenReturn(Optional.of(kebab));
        // when
        try {
            kebabServiceImpl.getKebabById(1);
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage(), is("Kebab is not active"));
        }
    }

    @Test
    void testDoesntGetKebabByIdThatIsNotPresent() {
        // given
        when(kebabRepository.findById(1)).thenReturn(Optional.empty());
        // when
        try {
            kebabServiceImpl.getKebabById(1);
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage(), is("Kebab not found"));
        }
    }

    /*
     * insertKebab()
     */

    @Test
    void testInsertKebab() {
        // given
        AddUpdateKebabRequest request = new AddUpdateKebabRequest("Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0);
        // when
        kebabServiceImpl.insertKebab(request);
        // then
        ArgumentCaptor<Kebab> kebabCaptor = ArgumentCaptor.forClass(Kebab.class);
        verify(kebabRepository).save(kebabCaptor.capture());
        Kebab capturedKebab = kebabCaptor.getValue();

        assertEquals("Kebab1", capturedKebab.getName());
        assertEquals("XL", capturedKebab.getSize());
        assertEquals("mieszane", capturedKebab.getMeat());
        assertEquals("sos czosnkowy", capturedKebab.getSauce());
        assertEquals(20.0, capturedKebab.getPrice());
        assertEquals(true, capturedKebab.getIsActive());
    }

    /*
     * updateKebab()
     */

    @Test
    void testUpdateKebab() {
        //given 
        Kebab kebab = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, true);
        AddUpdateKebabRequest request = new AddUpdateKebabRequest("Kebab2", "XL", "mieszane", "sos czosnkowy", 20.0);
        when(kebabRepository.findById(1)).thenReturn(Optional.of(kebab));
        //when
        kebabServiceImpl.updateKebab(1, request);
        //then
        ArgumentCaptor<Kebab> kebabCaptor = ArgumentCaptor.forClass(Kebab.class );
        verify(kebabRepository).save(kebabCaptor.capture());
        Kebab capturedKebab = kebabCaptor.getValue();
        assertEquals("Kebab2", capturedKebab.getName());
    }

    @Test
    void testDoesntUpdateKebabThatIsInactive() {
        // given
        Kebab kebab = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, false);
        AddUpdateKebabRequest request = new AddUpdateKebabRequest("Kebab2", "XL", "mieszane", "sos czosnkowy", 20.0);
        when(kebabRepository.findById(1)).thenReturn(Optional.of(kebab));
        // when
        try {
            kebabServiceImpl.updateKebab(1, request);
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage(), is("Kebab is not active"));
        }
    }

    @Test
    void testDoesntUpdateKebabThatIsNotPresent() {
        // given
        AddUpdateKebabRequest request = new AddUpdateKebabRequest("Kebab2", "XL", "mieszane", "sos czosnkowy", 20.0);
        when(kebabRepository.findById(1)).thenReturn(Optional.empty());
        // when
        try {
            kebabServiceImpl.updateKebab(1, request);
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage(), is("Kebab not found"));
        }
    }

    /*
     * deleteKebab()
     */

    @Test
    void testDeleteKebab() {
        // given
        Kebab kebab = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, true);
        when(kebabRepository.findById(1)).thenReturn(Optional.of(kebab));

        // when
        kebabServiceImpl.deleteKebab(1);

        // then
        verify(kebabRepository).delete(kebab);
    }

    @Test
    void testDoesntDeleteKebabThatIsNotPresent() {
        // given
        when(kebabRepository.findById(1)).thenReturn(Optional.empty());
        // when
        try {
            kebabServiceImpl.deleteKebab(1);
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage(), is("Kebab not found"));
        }
    }

    /*
     * deactivateKebab()
     */

    @Test
    void testDeactivateKebab() {
        // given
        Kebab kebab = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, true);
        when(kebabRepository.findById(1)).thenReturn(Optional.of(kebab));

        // when
        kebabServiceImpl.deactivateKebab(1);

        // then
        ArgumentCaptor<Kebab> kebabCaptor = ArgumentCaptor.forClass(Kebab.class);
        verify(kebabRepository).save(kebabCaptor.capture());
        Kebab capturedKebab = kebabCaptor.getValue();
        assertThat(capturedKebab.getIsActive(), is(false));
        assertThat(capturedKebab.getId(), is(1));
    }

    @Test
    void testDoesntDeactivateKebabThatIsNotPresent() {
        // given
        when(kebabRepository.findById(1)).thenReturn(Optional.empty());
        // when
        try {
            kebabServiceImpl.deactivateKebab(1);
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage(), is("Kebab not found"));
        }
    }

    @Test
    void testDoesntDeactivateKebabThatIsInactive() {
        // given
        Kebab kebab = new Kebab(1, "Kebab1", "XL", "mieszane", "sos czosnkowy", 20.0, false);
        when(kebabRepository.findById(1)).thenReturn(Optional.of(kebab));
        // when
        try {
            kebabServiceImpl.deactivateKebab(1);
        } catch (IllegalArgumentException e) {
            // then
            assertThat(e.getMessage(), is("Kebab is not active"));
        }
    }


}