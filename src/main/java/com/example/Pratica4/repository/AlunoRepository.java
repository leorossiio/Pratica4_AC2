// src/main/java/com/example/Pratica4/repository/AlunoRepository.java
package com.example.Pratica4.repository;

import com.example.Pratica4.domain.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}