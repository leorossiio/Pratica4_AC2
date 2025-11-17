// src/main/java/com/example/Pratica4/service/AlunoService.java    
package com.example.Pratica4.service;

import com.example.Pratica4.domain.Aluno;
import com.example.Pratica4.dto.AlunoMapper;
import com.example.Pratica4.dto.AlunoRequestDTO;
import com.example.Pratica4.dto.AlunoResponseDTO;
import com.example.Pratica4.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    public AlunoService(AlunoRepository alunoRepository, AlunoMapper alunoMapper) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
    }

    @Transactional
    public AlunoResponseDTO createAluno(AlunoRequestDTO requestDTO) {
        Aluno aluno = alunoMapper.toEntity(requestDTO);
        aluno = alunoRepository.save(aluno);
        return alunoMapper.toDTO(aluno);
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO getAlunoById(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado")); // TODO: Criar exceção customizada
        return alunoMapper.toDTO(aluno);
    }

    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> getAllAlunos() {
        return alunoRepository.findAll().stream()
                .map(alunoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public AlunoResponseDTO registrarParticipacaoNoForum(Long alunoId, String mensagem) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        aluno.registrarParticipacaoNoForum(mensagem);
        
        aluno = alunoRepository.save(aluno); 
        
        return alunoMapper.toDTO(aluno);
    }
}