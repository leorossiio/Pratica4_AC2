package com.example.Pratica4.repository;

import com.example.Pratica4.domain.Aluno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AlunoRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    void testSaveAndFindById() {
        // Arrange
        Aluno aluno = new Aluno("Teste repository", 5);
        
        // Act
        Aluno alunoSalvo = entityManager.persistAndFlush(aluno);
        Aluno alunoEncontrado = alunoRepository.findById(alunoSalvo.getId()).orElse(null);

        // Assert
        assertNotNull(alunoEncontrado);
        assertEquals(alunoSalvo.getId(), alunoEncontrado.getId());
        assertEquals("Teste Repo", alunoEncontrado.getNome());
        assertFalse(alunoEncontrado.isPremium());
    }
}