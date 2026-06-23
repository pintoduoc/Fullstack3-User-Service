package com.duoc.userservice.controller;

import com.duoc.userservice.model.Usuario;
import com.duoc.userservice.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    private Usuario usuarioMock;

    @BeforeEach
    void setUp() {
        usuarioMock = new Usuario();
        usuarioMock.setId(1L);
        usuarioMock.setRut("11111111-1");
        usuarioMock.setNombreCompleto("Juan Perez");
        usuarioMock.setContacto("juan@mail.com");
        usuarioMock.setRol(Usuario.Rol.CIUDADANO);
    }

    @Test
    void testFindAll() throws Exception {
        List<Usuario> usuarios = Arrays.asList(usuarioMock);
        when(usuarioService.findAll()).thenReturn(usuarios);

        mockMvc.perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].rut").value("11111111-1"));

        verify(usuarioService, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(usuarioService.findById(1L)).thenReturn(usuarioMock);

        mockMvc.perform(get("/api/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCompleto").value("Juan Perez"));

        verify(usuarioService, times(1)).findById(1L);
    }

    @Test
    void testFindByRut() throws Exception {
        when(usuarioService.findByRut("11111111-1")).thenReturn(usuarioMock);

        mockMvc.perform(get("/api/usuario/rut/11111111-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("11111111-1"));

        verify(usuarioService, times(1)).findByRut("11111111-1");
    }

    @Test
    void testFindByRol() throws Exception {
        when(usuarioService.findByRol(Usuario.Rol.BRIGADISTA)).thenReturn(Arrays.asList(usuarioMock));

        mockMvc.perform(get("/api/usuario/rol").param("rol", "BRIGADISTA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

        verify(usuarioService, times(1)).findByRol(Usuario.Rol.BRIGADISTA);
    }

    @Test
    void testCreateUsuario() throws Exception {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioMock);

        mockMvc.perform(post("/api/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rut\":\"11111111-1\",\"nombreCompleto\":\"Juan Perez\",\"rol\":\"CIUDADANO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("11111111-1"));

        verify(usuarioService, times(1)).save(any(Usuario.class));
    }

    @Test
    void testUpdateUsuario() throws Exception {
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuarioMock);

        mockMvc.perform(put("/api/usuario/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rut\":\"11111111-1\",\"nombreCompleto\":\"Juan Perez\",\"rol\":\"CIUDADANO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("11111111-1"));

        verify(usuarioService, times(1)).save(any(Usuario.class));
    }

    @Test
    void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).deleteById(1L);

        mockMvc.perform(delete("/api/usuario/1"))
                .andExpect(status().isOk());

        verify(usuarioService, times(1)).deleteById(1L);
    }
}
