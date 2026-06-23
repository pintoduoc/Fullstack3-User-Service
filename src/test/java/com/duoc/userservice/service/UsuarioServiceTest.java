package com.duoc.userservice.service;

import com.duoc.userservice.model.Usuario;
import com.duoc.userservice.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setRut("11111111-1");
        usuarioMock.setNombreCompleto("Juan Perez");
        usuarioMock.setRol(Usuario.Rol.CIUDADANO);
    }

    @Test
    void testFindAll_Exitoso() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuarioMock));
        List<Usuario> resultado = usuarioService.findAll();
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan Perez", resultado.get(0).getNombreCompleto());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void testFindById_Exitoso() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        Usuario resultado = usuarioService.findById(1L);
        assertNotNull(resultado);
        assertEquals("11111111-1", resultado.getRut());
        verify(usuarioRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NoEncontrado() {
        when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        Usuario resultado = usuarioService.findById(99L);
        assertNull(resultado);
        verify(usuarioRepository, times(1)).findById(99L);
    }

    @Test
    void testBuscarPorRut_Exitoso() {
        when(usuarioRepository.findByRut("11111111-1")).thenReturn(usuarioMock);

        Usuario resultado = usuarioService.findByRut("11111111-1");

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombreCompleto());
        assertEquals("11111111-1", resultado.getRut());
    }

    @Test
    void testFindByRol_Exitoso() {
        when(usuarioRepository.findByRol(Usuario.Rol.CIUDADANO)).thenReturn(Arrays.asList(usuarioMock));
        List<Usuario> resultado = usuarioService.findByRol(Usuario.Rol.CIUDADANO);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(Usuario.Rol.CIUDADANO, resultado.get(0).getRol());
        verify(usuarioRepository, times(1)).findByRol(Usuario.Rol.CIUDADANO);
    }

    @Test
    void testGuardarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario guardado = usuarioService.save(usuarioMock);

        assertNotNull(guardado);
        assertEquals(Usuario.Rol.CIUDADANO, guardado.getRol());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testDeleteById_Exitoso() {
        doNothing().when(usuarioRepository).deleteById(1L);
        usuarioService.deleteById(1L);
        verify(usuarioRepository, times(1)).deleteById(1L);
    }
}