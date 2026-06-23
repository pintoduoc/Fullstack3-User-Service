package com.duoc.userservice.controller;

import com.duoc.userservice.model.Usuario;
import com.duoc.userservice.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Test
    void testFindAll() throws Exception {
        Usuario usuario = new Usuario(1L, "11111111-1", "Juan Perez", "contacto", Usuario.Rol.CIUDADANO, null);
        when(usuarioService.findAll()).thenReturn(Arrays.asList(usuario));

        mockMvc.perform(get("/api/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rut").value("11111111-1"))
                .andExpect(jsonPath("$[0].nombreCompleto").value("Juan Perez"));
    }

    @Test
    void testFindById() throws Exception {
        Usuario usuario = new Usuario(1L, "11111111-1", "Juan Perez", null, Usuario.Rol.CIUDADANO, null);
        when(usuarioService.findById(1L)).thenReturn(usuario);

        mockMvc.perform(get("/api/usuario/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("11111111-1"));
    }

    @Test
    void testFindByRut() throws Exception {
        Usuario usuario = new Usuario(1L, "11111111-1", "Juan Perez", null, Usuario.Rol.CIUDADANO, null);
        when(usuarioService.findByRut("11111111-1")).thenReturn(usuario);

        mockMvc.perform(get("/api/usuario/rut/11111111-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCompleto").value("Juan Perez"));
    }

    @Test
    void testFindByRol() throws Exception {
        Usuario usuario = new Usuario(1L, "11111111-1", "Juan Perez", null, Usuario.Rol.BRIGADISTA, null);
        when(usuarioService.findByRol(Usuario.Rol.BRIGADISTA)).thenReturn(Arrays.asList(usuario));

        mockMvc.perform(get("/api/usuario/rol").param("rol", "BRIGADISTA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].rol").value("BRIGADISTA"));
    }

    @Test
    void testCreateUsuario() throws Exception {
        Usuario usuario = new Usuario(1L, "11111111-1", "Juan Perez", null, Usuario.Rol.CIUDADANO, null);
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/api/usuario")
                        .contentType("application/json")
                        .content("{\"rut\":\"11111111-1\",\"nombreCompleto\":\"Juan Perez\",\"rol\":\"CIUDADANO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("11111111-1"));
    }

    @Test
    void testUpdateUsuario() throws Exception {
        Usuario usuario = new Usuario(1L, "11111111-1", "Juan Perez Actualizado", null, Usuario.Rol.CIUDADANO, null);
        when(usuarioService.save(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/api/usuario/1")
                        .contentType("application/json")
                        .content("{\"rut\":\"11111111-1\",\"nombreCompleto\":\"Juan Perez Actualizado\",\"rol\":\"CIUDADANO\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreCompleto").value("Juan Perez Actualizado"));
    }

    @Test
    void testDeleteUsuario() throws Exception {
        mockMvc.perform(delete("/api/usuario/1"))
                .andExpect(status().isOk());
    }
}
