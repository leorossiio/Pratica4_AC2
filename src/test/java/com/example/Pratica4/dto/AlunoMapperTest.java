package com.example.Pratica4.dto;

import org.junit.jupiter.api.Test;

import com.example.Pratica4.entity.Aluno;

import static org.junit.jupiter.api.Assertions.*;

class AlunoMapperTest {

    private AlunoMapper mapper = new AlunoMapper();

    @Test
    void testConstrutor() {
        // Testa o construtor vazio para cobertura
        AlunoMapper newMapper = new AlunoMapper();
        assertNotNull(newMapper);
    }

    @Test
    void testToEntityComNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void testToDTOComNull() {
        assertNull(mapper.toDTO(null));
    }

    @Test
    void testToEntityMapeamento() {
        // Arrange
        AlunoRequestDTO dto = new AlunoRequestDTO();
        dto.setNome("Teste Mapper");
        dto.setCursosConcluidos(5);

        // Act
        Aluno entity = mapper.toEntity(dto);

        // Assert
        assertNotNull(entity);
        assertEquals("Teste Mapper", entity.getNome());
        assertEquals(5, entity.getCursosConcluidos());
    }

    @Test
    void testToDTOMapeamento() {
        // Arrange
        Aluno entity = new Aluno("Teste Entity", 12);
        entity.setId(1L); // Simula entidade salva

        // Act
        AlunoResponseDTO dto = mapper.toDTO(entity);

        // Assert
        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Teste Entity", dto.getNome());
        assertEquals(12, dto.getCursosConcluidos());
        assertTrue(dto.isPremium());
    }
}