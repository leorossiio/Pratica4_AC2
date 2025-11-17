// src/test/java/com/example/Pratica4/service/AlunoServiceTest.java

package com.example.Pratica4.service;

import com.example.Pratica4.domain.Aluno;
import com.example.Pratica4.dto.AlunoMapper;
import com.example.Pratica4.dto.AlunoRequestDTO;
import com.example.Pratica4.dto.AlunoResponseDTO;
import com.example.Pratica4.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private AlunoMapper alunoMapper;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void testCreateAluno() {
        // Arrange
        AlunoRequestDTO request = new AlunoRequestDTO();
        request.setNome("Novo Aluno");
        request.setCursosConcluidos(2);

        Aluno aluno = new Aluno("Novo Aluno", 2);
        AlunoResponseDTO responseDTO = new AlunoResponseDTO();
        responseDTO.setId(1L);

        when(alunoMapper.toEntity(request)).thenReturn(aluno);
        when(alunoRepository.save(aluno)).thenReturn(aluno);
        when(alunoMapper.toDTO(aluno)).thenReturn(responseDTO);

        // Act
        AlunoResponseDTO resultado = alunoService.createAluno(request);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void testGetAlunoById() {
        // Arrange
        Aluno aluno = new Aluno("Aluno Existente", 5);
        AlunoResponseDTO responseDTO = new AlunoResponseDTO();
        responseDTO.setId(1L);

        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(alunoMapper.toDTO(aluno)).thenReturn(responseDTO);

        // Act
        AlunoResponseDTO resultado = alunoService.getAlunoById(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void testGetAlunoById_NotFound() {
        // Arrange
        when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            alunoService.getAlunoById(99L);
        });
    }

    @Test
    void testGetAllAlunos() {
        // Arrange
        Aluno aluno1 = new Aluno("Aluno 1", 3);
        Aluno aluno2 = new Aluno("Aluno 2", 13);
        List<Aluno> alunos = List.of(aluno1, aluno2);

        when(alunoRepository.findAll()).thenReturn(alunos);
        when(alunoMapper.toDTO(any(Aluno.class))).thenReturn(new AlunoResponseDTO());

        // Act
        List<AlunoResponseDTO> resultados = alunoService.getAllAlunos();

        // Assert
        assertEquals(2, resultados.size());
        verify(alunoRepository, times(1)).findAll();
    }

    @Test
    void testRegistrarParticipacaoNoForum() {
        // Arrange
        Aluno aluno = new Aluno("Aluno Forum", 11); // comeca com 11
        
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);
        when(alunoMapper.toDTO(aluno)).thenReturn(new AlunoResponseDTO()); // Retorno n importa muito

        // Act
        alunoService.registrarParticipacaoNoForum(1L, "teste");

        // Assert
        verify(alunoRepository, times(1)).findById(1L);
        verify(alunoRepository, times(1)).save(aluno);
        assertEquals(12, aluno.getCursosConcluidos()); // Verifica se a rn foi chamada
        assertTrue(aluno.isPremium());
    }

    // Adicionar estes métodos dentro da classe AlunoServiceTest existente

    @Test
    void testRegistrarParticipacaoNoForum_NotFound() {
        // Arrange
        when(alunoRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            alunoService.registrarParticipacaoNoForum(99L, "teste");
        });

        // Verifica se o save NUNCA foi chamado
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    void testGetAllAlunos_EmptyList() {
        // Arrange
        when(alunoRepository.findAll()).thenReturn(List.of()); // Retorna lista vazia

        // Act
        List<AlunoResponseDTO> resultados = alunoService.getAllAlunos();

        // Assert
        assertTrue(resultados.isEmpty());
        verify(alunoRepository, times(1)).findAll();
        verify(alunoMapper, never()).toDTO(any(Aluno.class)); // Mapper não deve ser chamado
    }
}