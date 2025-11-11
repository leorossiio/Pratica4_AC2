package com.example.Pratica4.controller;

import com.example.Pratica4.dto.AlunoRequestDTO;
import com.example.Pratica4.dto.AlunoResponseDTO;
import com.example.Pratica4.service.AlunoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping
    public ResponseEntity<AlunoResponseDTO> createAluno(@RequestBody AlunoRequestDTO requestDTO) {
        AlunoResponseDTO responseDTO = alunoService.createAluno(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoResponseDTO> getAlunoById(@PathVariable Long id) {
        AlunoResponseDTO responseDTO = alunoService.getAlunoById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<AlunoResponseDTO>> getAllAlunos() {
        List<AlunoResponseDTO> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @PostMapping("/{id}/forum")
    public ResponseEntity<AlunoResponseDTO> registrarParticipacao(
            @PathVariable Long id, 
            @RequestBody Map<String, String> body) {
        
        String mensagem = body.getOrDefault("mensagem", "Participação registrada"); 
        
        AlunoResponseDTO responseDTO = alunoService.registrarParticipacaoNoForum(id, mensagem);
        return ResponseEntity.ok(responseDTO);
    }
}