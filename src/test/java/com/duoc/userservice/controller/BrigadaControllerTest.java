package com.duoc.userservice.controller;

import com.duoc.userservice.model.Brigada;
import com.duoc.userservice.service.BrigadaService;
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

@WebMvcTest(BrigadaController.class)
class BrigadaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BrigadaService brigadaService;

    @Test
    void testFindAll() throws Exception {
        Brigada brigada = new Brigada(1L, "Brigada Alfa", "Norte");
        when(brigadaService.findAll()).thenReturn(Arrays.asList(brigada));

        mockMvc.perform(get("/api/brigada"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Brigada Alfa"))
                .andExpect(jsonPath("$[0].sector").value("Norte"));
    }

    @Test
    void testFindById() throws Exception {
        Brigada brigada = new Brigada(1L, "Brigada Alfa", "Norte");
        when(brigadaService.findById(1L)).thenReturn(brigada);

        mockMvc.perform(get("/api/brigada/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Brigada Alfa"));
    }

    @Test
    void testCreateBrigada() throws Exception {
        Brigada brigada = new Brigada(1L, "Brigada Nueva", "Sur");
        when(brigadaService.save(any(Brigada.class))).thenReturn(brigada);

        mockMvc.perform(post("/api/brigada")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Brigada Nueva\",\"sector\":\"Sur\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Brigada Nueva"));
    }

    @Test
    void testUpdateBrigada() throws Exception {
        Brigada brigada = new Brigada(1L, "Brigada Actualizada", "Este");
        when(brigadaService.save(any(Brigada.class))).thenReturn(brigada);

        mockMvc.perform(put("/api/brigada/1")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Brigada Actualizada\",\"sector\":\"Este\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Brigada Actualizada"));
    }

    @Test
    void testDeleteBrigada() throws Exception {
        mockMvc.perform(delete("/api/brigada/1"))
                .andExpect(status().isOk());
    }
}
