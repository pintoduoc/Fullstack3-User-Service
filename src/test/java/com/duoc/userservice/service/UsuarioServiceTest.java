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
    void testBuscarPorRut_Exitoso() {
        when(usuarioRepository.findByRut("11111111-1")).thenReturn(usuarioMock);

        Usuario resultado = usuarioService.findByRut("11111111-1");

        assertNotNull(resultado);
        assertEquals("Juan Perez", resultado.getNombreCompleto());
        assertEquals("11111111-1", resultado.getRut());
    }

    @Test
    void testGuardarUsuario() {
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario guardado = usuarioService.save(usuarioMock);

        assertNotNull(guardado);
        assertEquals(Usuario.Rol.CIUDADANO, guardado.getRol());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
}