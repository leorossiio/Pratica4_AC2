package com.example.Pratica4.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoDTOTest {

    @Test
    void testAlunoRequestDTO() {
        // Arrange
        AlunoRequestDTO dto = new AlunoRequestDTO();
        
        // Act
        dto.setNome("Nome Req");
        dto.setCursosConcluidos(10);

        // Assert
        assertEquals("Nome Req", dto.getNome());
        assertEquals(10, dto.getCursosConcluidos());
    }

    @Test
    void testAlunoResponseDTO() {
        // Arrange
        AlunoResponseDTO dto = new AlunoResponseDTO();
        
        // Act
        dto.setId(1L);
        dto.setNome("Nome Res");
        dto.setCursosConcluidos(15);
        dto.setPremium(true);

        // Assert
        assertEquals(1L, dto.getId());
        assertEquals("Nome Res", dto.getNome());
        assertEquals(15, dto.getCursosConcluidos());
        assertTrue(dto.isPremium());
    }
}