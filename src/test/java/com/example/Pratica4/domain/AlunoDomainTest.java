package com.example.Pratica4.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    void testCriacaoAlunoNaoPremium() {
        // Arrange
        String nome = "Leonardo";
        int cursos = 10;
        
        // Act
        Aluno aluno = new Aluno(nome, cursos);

        // Assert
        assertEquals(nome, aluno.getNome());
        assertEquals(cursos, aluno.getCursosConcluidos());
        assertFalse(aluno.isPremium());
    }

    @Test
    void testCriacaoAlunoPremium() {
        // Arrange
        Aluno aluno = new Aluno("Guilherme", 12);

        // Act
        boolean isPremium = aluno.isPremium();

        // Assert
        assertEquals(12, aluno.getCursosConcluidos());
        assertTrue(isPremium);
    }

    @Test
    void testParticiparForumIncrementaCurso() {
        // Arrange
        Aluno aluno = new Aluno("Maria", 5);
        
        // Act
        aluno.registrarParticipacaoNoForum("Minha mensagem de teste");

        // Assert
        assertEquals(6, aluno.getCursosConcluidos());
        assertFalse(aluno.isPremium());
    }

    @Test
    void testParticiparForumTornaPremium() {
        // Arrange
        Aluno aluno = new Aluno("Joao", 11);
        
        // Act
        aluno.registrarParticipacaoNoForum("Quase");

        // Assert
        assertEquals(12, aluno.getCursosConcluidos());
        assertTrue(aluno.isPremium());
    }

    @Test
    void testNomeNuloOuVazio() {
        // Arrange, Act, Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno(null, 5); // Act
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Aluno("   ", 5); // Act
        });
    }

    @Test
    void testEqualsHashCodeValueObject() {
        // Arrange
        StatusAluno status1 = StatusAluno.comCursos(10);
        StatusAluno status2 = StatusAluno.comCursos(10);
        StatusAluno status3 = StatusAluno.comCursos(11);

        // Act

        // Assert
        assertEquals(status1, status2);
        assertNotEquals(status1, status3);
        assertEquals(status1.hashCode(), status2.hashCode());
    }
}