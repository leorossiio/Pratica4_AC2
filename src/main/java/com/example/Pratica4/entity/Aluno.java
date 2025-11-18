package com.example.Pratica4.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // construtor JPA
@ToString(exclude = {"status"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    private String email;

    @Embedded
    private StatusAluno status;

    // Construtores de conveniência
    public Aluno(String nome, int cursosConcluidos) {
        setNome(nome); // usa validação
        this.status = StatusAluno.comCursos(cursosConcluidos);
    }

    public Aluno(Long id, String nome, String email, StatusAluno status) {
        this.id = id;
        setNome(nome);
        this.email = email;
        this.status = status;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    public void registrarParticipacaoNoForum(String mensagem) {
        int atual = this.getCursosConcluidos();
        this.status = StatusAluno.comCursos(atual + 1);
    }

    public boolean isPremium() {
        return this.status != null && this.status.isPremium();
    }

    public int getCursosConcluidos() {
        return (this.status == null) ? 0 : this.status.getCursosConcluidos();
    }
}
