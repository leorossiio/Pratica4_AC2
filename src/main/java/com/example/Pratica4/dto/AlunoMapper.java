// src/main/java/com/example/Pratica4/dto/AlunoMapper.java
package com.example.Pratica4.dto;

import org.springframework.stereotype.Component;

import com.example.Pratica4.entity.Aluno;


@Component
public class AlunoMapper {

    public AlunoMapper() {}

    public Aluno toEntity(AlunoRequestDTO dto) {
        if (dto == null) return null;
        return new Aluno(dto.getNome(), dto.getCursosConcluidos());
    }

    public AlunoResponseDTO toDTO(Aluno aluno) {
        if (aluno == null) return null;
        AlunoResponseDTO dto = new AlunoResponseDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setCursosConcluidos(aluno.getCursosConcluidos());
        dto.setPremium(aluno.isPremium());
        return dto;
    }
}