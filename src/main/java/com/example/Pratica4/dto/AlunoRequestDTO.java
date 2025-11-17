// src/main/java/com/example/Pratica4/dto/AlunoRequestDTO.java

package com.example.Pratica4.dto;

public class AlunoRequestDTO {
    private String nome;
    private int cursosConcluidos;

    public AlunoRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCursosConcluidos() { return cursosConcluidos; }
    public void setCursosConcluidos(int cursosConcluidos) { this.cursosConcluidos = cursosConcluidos; }
}