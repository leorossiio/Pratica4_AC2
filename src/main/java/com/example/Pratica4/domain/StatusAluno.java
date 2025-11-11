package com.example.Pratica4.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatusAluno {

    private static final int CURSOS_PREMIUM = 12;

    private int cursosConcluidos;
    private boolean premium;

    private StatusAluno(int cursosConcluidos) {
        this.cursosConcluidos = cursosConcluidos;
        this.premium = (cursosConcluidos >= CURSOS_PREMIUM);
    }

    public static StatusAluno inicial() {
        return new StatusAluno(0);
    }
    
    public static StatusAluno comCursos(int totalCursos) {
        return new StatusAluno(totalCursos);
    }

    public StatusAluno adicionarCurso() {
        return new StatusAluno(this.cursosConcluidos + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatusAluno that = (StatusAluno) o;
        return cursosConcluidos == that.cursosConcluidos && premium == that.premium;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(cursosConcluidos, premium);
    }
}