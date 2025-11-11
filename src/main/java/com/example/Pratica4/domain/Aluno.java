package com.example.Pratica4.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "alunos")
@Getter
@NoArgsConstructor
@ToString(exclude = {"status"})
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Embedded
    private StatusAluno status;
    
    public Aluno(String nome, int cursosConcluidos) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
        this.status = StatusAluno.comCursos(cursosConcluidos);
    }

    public void registrarParticipacaoNoForum(String mensagem) {
        this.status = this.status.adicionarCurso();
    }
    
    public boolean isPremium() {
        if (this.status == null) {
            return false;
        }
        return this.status.isPremium();
    }
    
    public int getCursosConcluidos() {
        if (this.status == null) {
            return 0;
        }
        return this.status.getCursosConcluidos();
    }
}