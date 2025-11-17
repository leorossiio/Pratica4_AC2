// src/test/java/com/example/Pratica4/domain/AlunoDomainTest.java
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

    // Adicionar estes métodos dentro da classe AlunoTest existente

    // --- Testes de Aluno (Entidade) ---

    @Test
    void testConstrutorCompletoEGetters() {
        // Arrange
        StatusAluno status = StatusAluno.comCursos(5);
        Aluno aluno = new Aluno(1L, "Completo", "email@teste.com", status);

        // Act & Assert
        assertEquals(1L, aluno.getId());
        assertEquals("Completo", aluno.getNome());
        assertEquals("email@teste.com", aluno.getEmail());
        assertEquals(status, aluno.getStatus());
        assertEquals(5, aluno.getCursosConcluidos());
    }

    @Test
    void testSetEmailEStatus() {
        // Arrange
        Aluno aluno = new Aluno("Teste", 1);
        StatusAluno novoStatus = StatusAluno.comCursos(10);
        
        // Act
        aluno.setEmail("novo@email.com");
        aluno.setStatus(novoStatus);

        // Assert
        assertEquals("novo@email.com", aluno.getEmail());
        assertEquals(novoStatus, aluno.getStatus());
    }

    @Test
    void testConstrutorProtegidoAluno() {
        // Arrange & Act
        Aluno aluno = new Aluno(); // Usado pelo JPA
        aluno.setId(10L); // Apenas para teste
        
        // Assert
        assertNotNull(aluno);
        assertEquals(10L, aluno.getId());
    }

    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Aluno aluno1 = new Aluno(1L, "Aluno 1", "email1", StatusAluno.comCursos(1));
        Aluno aluno2 = new Aluno(1L, "Aluno 2", "email2", StatusAluno.comCursos(2)); // Mesmo ID
        Aluno aluno3 = new Aluno(2L, "Aluno 1", "email1", StatusAluno.comCursos(1)); // ID diferente
        
        // Assert
        assertEquals(aluno1, aluno2); // Iguais pelo ID
        assertNotEquals(aluno1, aluno3); // Diferentes pelo ID
        assertEquals(aluno1.hashCode(), aluno2.hashCode());
        assertNotEquals(aluno1.hashCode(), aluno3.hashCode());
        
        // Teste de robustez do equals
        assertNotEquals(aluno1, null);
        assertNotEquals(aluno1, new Object());
        assertEquals(aluno1, aluno1);
    }
    
    @Test
    void testToString() {
        // Arrange
        Aluno aluno = new Aluno(1L, "Aluno 1", "email1", StatusAluno.comCursos(1));
        
        // Act
        String str = aluno.toString();
        
        // Assert
        assertTrue(str.contains("id=1"));
        assertTrue(str.contains("nome=Aluno 1"));
        assertFalse(str.contains("status=")); // Verificando o exclude
    }

    @Test
    void testGetCursosConcluidosComStatusNulo() {
        // Arrange
        Aluno aluno = new Aluno(); // Status é nulo
        
        // Assert
        assertEquals(0, aluno.getCursosConcluidos());
    }


    // --- Testes de StatusAluno (Value Object) ---

    @Test
    void testStatusAlunoConstrutorProtegido() {
        // Arrange & Act
        StatusAluno status = new StatusAluno(); // Usado pelo JPA
        
        // Assert
        assertNotNull(status);
        assertEquals(0, status.getCursosConcluidos());
        assertFalse(status.isPremium());
    }
    
    @Test
    void testComCursosNegativos() {
        // Arrange & Act
        StatusAluno status = StatusAluno.comCursos(-5);
        
        // Assert
        assertEquals(0, status.getCursosConcluidos());
        assertFalse(status.isPremium());
    }

    @Test
    void testAddCursos() {
        // Arrange
        StatusAluno statusInicial = StatusAluno.comCursos(5);

        // Act
        StatusAluno statusNovo = statusInicial.addCursos(3);

        // Assert
        assertEquals(5, statusInicial.getCursosConcluidos()); // Imutabilidade: original não muda
        assertEquals(8, statusNovo.getCursosConcluidos());
    }

    @Test
    void testAddCursosParaNegativo() {
        // Arrange
        StatusAluno statusInicial = StatusAluno.comCursos(5);
        
        // Act
        StatusAluno statusNovo = statusInicial.addCursos(-10);
        
        // Assert
        assertEquals(0, statusNovo.getCursosConcluidos()); // Deve ser no máximo 0
    }

    @Test
    void testIsPremiumNoLimite() {
        // Arrange
        StatusAluno status11 = StatusAluno.comCursos(11);
        StatusAluno status12 = StatusAluno.comCursos(12);
        StatusAluno status13 = StatusAluno.comCursos(13);
        
        // Assert
        assertFalse(status11.isPremium());
        assertTrue(status12.isPremium());
        assertTrue(status13.isPremium());
    }
}