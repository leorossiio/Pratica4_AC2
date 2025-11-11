package com.example.Pratica4.dto;

import lombok.Data;

@Data
public class AlunoResponseDTO {
    private Long id;
    private String nome;
    private int cursosConcluidos;
    private boolean premium;
}