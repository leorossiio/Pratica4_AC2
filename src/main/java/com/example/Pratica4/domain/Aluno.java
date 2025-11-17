// File: src/main/java/com/example/Pratica4/domain/Aluno.java
package com.example.Pratica4.domain;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade Aluno com validação no nome (lança IllegalArgumentException para null/empty)
 */
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

    /**
     * Setter com validação exigida pelos testes: nome não pode ser null/blank.
     */
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    // O lombok ainda gera outros setters/getters (como setEmail) porque não existem aqui.
    // Se lombok gerar setNome, ele não sobrescreverá este método porque já existe uma implementação.

    /**
     * Registra participação no fórum: incrementa 1 curso por participação (regra atual do domínio).
     */
    public void registrarParticipacaoNoForum(String mensagem) {
        int atual = this.getCursosConcluidos();
        this.status = StatusAluno.comCursos(atual + 1);
    }

    /**
     * Conveniência para verificar se é premium.
     */
    public boolean isPremium() {
        return this.status != null && this.status.isPremium();
    }

    /**
     * Conveniência para obter cursos concluídos a partir do status embutido.
     */
    public int getCursosConcluidos() {
        return (this.status == null) ? 0 : this.status.getCursosConcluidos();
    }
}
