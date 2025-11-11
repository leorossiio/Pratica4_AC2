package com.example.Pratica4.controller;

import com.example.Pratica4.dto.AlunoRequestDTO;
import com.example.Pratica4.dto.AlunoResponseDTO;
import com.example.Pratica4.service.AlunoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AlunoService alunoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAluno() throws Exception {
        // Arrange
        AlunoRequestDTO request = new AlunoRequestDTO();
        request.setNome("Aluno Web");
        request.setCursosConcluidos(1);

        AlunoResponseDTO response = new AlunoResponseDTO();
        response.setId(1L);
        response.setNome("Aluno Web");

        when(alunoService.createAluno(any(AlunoRequestDTO.class))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Aluno Web"));
    }

    @Test
    void testGetAlunoById() throws Exception {
        // Arrange
        AlunoResponseDTO response = new AlunoResponseDTO();
        response.setId(1L);
        response.setNome("Aluno Encontrado");

        when(alunoService.getAlunoById(1L)).thenReturn(response);

        // Act & Assert
        mockMvc.perform(get("/api/alunos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Aluno Encontrado"));
    }

    @Test
    void testGetAllAlunos() throws Exception {
        // Arrange
        AlunoResponseDTO response = new AlunoResponseDTO();
        response.setId(1L);
        
        when(alunoService.getAllAlunos()).thenReturn(List.of(response));

        // Act & Assert
        mockMvc.perform(get("/api/alunos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L));
    }

    @Test
    void testRegistrarParticipacao() throws Exception {
        // Arrange
        AlunoResponseDTO response = new AlunoResponseDTO();
        response.setId(1L);
        response.setCursosConcluidos(12);
        response.setPremium(true);
        
        String mensagem = "Minha participacao";
        Map<String, String> body = Map.of("mensagem", mensagem);

        when(alunoService.registrarParticipacaoNoForum(eq(1L), eq(mensagem))).thenReturn(response);

        // Act & Assert
        mockMvc.perform(post("/api/alunos/1/forum")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cursosConcluidos").value(12))
                .andExpect(jsonPath("$.premium").value(true));
    }
}