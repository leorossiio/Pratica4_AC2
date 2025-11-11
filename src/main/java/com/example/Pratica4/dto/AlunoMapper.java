package com.example.Pratica4.dto;

import com.example.Pratica4.domain.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno toEntity(AlunoRequestDTO dto) {
        return new Aluno(dto.getNome(), dto.getCursosConcluidos());
    }

    public AlunoResponseDTO toDTO(Aluno entity) {
        AlunoResponseDTO dto = new AlunoResponseDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCursosConcluidos(entity.getCursosConcluidos()); 
        dto.setPremium(entity.isPremium());
        return dto;
    }
}