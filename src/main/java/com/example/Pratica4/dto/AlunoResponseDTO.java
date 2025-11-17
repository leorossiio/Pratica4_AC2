// src/main/java/com/example/Pratica4/dto/AlunoResponseDTO.java
package com.example.Pratica4.dto;

public class AlunoResponseDTO {
    private Long id;
    private String nome;
    private int cursosConcluidos;
    private boolean premium;

    public AlunoResponseDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getCursosConcluidos() { return cursosConcluidos; }
    public void setCursosConcluidos(int cursosConcluidos) { this.cursosConcluidos = cursosConcluidos; }

    public boolean isPremium() { return premium; }
    public void setPremium(boolean premium) { this.premium = premium; }
}