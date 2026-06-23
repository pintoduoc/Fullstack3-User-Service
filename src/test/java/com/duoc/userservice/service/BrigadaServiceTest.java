package com.duoc.userservice.service;

import com.duoc.userservice.model.Brigada;
import com.duoc.userservice.repository.BrigadaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrigadaServiceTest {

    @Mock
    private BrigadaRepository brigadaRepository;

    @InjectMocks
    private BrigadaService brigadaService;

    private Brigada brigadaMock;

    @BeforeEach
    void setUp() {
        brigadaMock = new Brigada();
        brigadaMock.setId(1L);
        brigadaMock.setNombre("Brigada Alfa");
        brigadaMock.setSector("Norte");
    }

    @Test
    void testFindAll_Exitoso() {
        when(brigadaRepository.findAll()).thenReturn(Arrays.asList(brigadaMock));

        List<Brigada> resultado = brigadaService.findAll();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Brigada Alfa", resultado.get(0).getNombre());
        verify(brigadaRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Exitoso() {
        when(brigadaRepository.findById(1L)).thenReturn(Optional.of(brigadaMock));

        Brigada resultado = brigadaService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Brigada Alfa", resultado.getNombre());
        verify(brigadaRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NoExistente() {
        when(brigadaRepository.findById(99L)).thenReturn(Optional.empty());

        Brigada resultado = brigadaService.findById(99L);

        assertNull(resultado);
        verify(brigadaRepository, times(1)).findById(99L);
    }

    @Test
    void testSave_Exitoso() {
        when(brigadaRepository.save(any(Brigada.class))).thenReturn(brigadaMock);

        Brigada resultado = brigadaService.save(brigadaMock);

        assertNotNull(resultado);
        assertEquals("Brigada Alfa", resultado.getNombre());
        assertEquals("Norte", resultado.getSector());
        verify(brigadaRepository, times(1)).save(any(Brigada.class));
    }

    @Test
    void testDeleteById_Exitoso() {
        doNothing().when(brigadaRepository).deleteById(1L);

        brigadaService.deleteById(1L);

        verify(brigadaRepository, times(1)).deleteById(1L);
    }
}
