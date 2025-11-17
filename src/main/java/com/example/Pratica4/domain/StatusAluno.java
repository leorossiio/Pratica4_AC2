// src/main/java/com/example/Pratica4/domain/StatusAluno.java
package com.example.Pratica4.domain;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode
public class StatusAluno {

    private int cursosConcluidos;
    private boolean premium;

    // Obrigatório para JPA
    protected StatusAluno() {
    }

    public StatusAluno(int cursosConcluidos) {
        this.cursosConcluidos = Math.max(0, cursosConcluidos);
        this.premium = this.cursosConcluidos >= 12;
    }

    /**
     * Método estático de fábrica: permite chamadas como StatusAluno.comCursos(6)
     * (os testes do projeto usam essa forma).
     */
    public static StatusAluno comCursos(int cursos) {
        return new StatusAluno(cursos);
    }

    /**
     * Método de instância para criar um novo Status acumulando cursos adicionais.
     */
    public StatusAluno addCursos(int cursosAdicionais) {
        int total = Math.max(0, this.cursosConcluidos + cursosAdicionais);
        return new StatusAluno(total);
    }

    public boolean isPremium() {
        return premium;
    }
}