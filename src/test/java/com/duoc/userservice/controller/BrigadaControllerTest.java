package com.duoc.userservice.controller;

import com.duoc.userservice.model.Brigada;
import com.duoc.userservice.service.BrigadaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrigadaController.class)
class BrigadaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BrigadaService brigadaService;

    private Brigada brigadaMock;

    @BeforeEach
    void setUp() {
        brigadaMock = new Brigada();
        brigadaMock.setId(1L);
        brigadaMock.setNombre("Brigada de Rescate");
        brigadaMock.setSector("Temuco");
    }

    @Test
    void testFindAll() throws Exception {
        when(brigadaService.findAll()).thenReturn(Arrays.asList(brigadaMock));

        mockMvc.perform(get("/api/brigada"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Brigada de Rescate"));

        verify(brigadaService, times(1)).findAll();
    }

    @Test
    void testFindById() throws Exception {
        when(brigadaService.findById(1L)).thenReturn(brigadaMock);

        mockMvc.perform(get("/api/brigada/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sector").value("Temuco"));

        verify(brigadaService, times(1)).findById(1L);
    }

    @Test
    void testCreateBrigada() throws Exception {
        when(brigadaService.save(any(Brigada.class))).thenReturn(brigadaMock);

        mockMvc.perform(post("/api/brigada")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Brigada de Rescate\",\"sector\":\"Temuco\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Brigada de Rescate"));

        verify(brigadaService, times(1)).save(any(Brigada.class));
    }

    @Test
    void testUpdateBrigada() throws Exception {
        when(brigadaService.save(any(Brigada.class))).thenReturn(brigadaMock);

        mockMvc.perform(put("/api/brigada/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\":\"Brigada de Rescate\",\"sector\":\"Temuco\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sector").value("Temuco"));

        verify(brigadaService, times(1)).save(any(Brigada.class));
    }

    @Test
    void testDeleteBrigada() throws Exception {
        doNothing().when(brigadaService).deleteById(1L);

        mockMvc.perform(delete("/api/brigada/1"))
                .andExpect(status().isOk());

        verify(brigadaService, times(1)).deleteById(1L);
    }
}
