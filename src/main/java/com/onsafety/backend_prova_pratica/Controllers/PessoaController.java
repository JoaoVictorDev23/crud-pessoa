package com.onsafety.backend_prova_pratica.Controllers;


import com.onsafety.backend_prova_pratica.DTO.PessoaDTO;
import com.onsafety.backend_prova_pratica.Services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para operações com a entidade Pessoa.
 */
@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    @Autowired
    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> createPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO createdPessoa = pessoaService.createPessoa(pessoaDTO);
        return new ResponseEntity<>(createdPessoa, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> getPessoaById(@PathVariable Long id) {
        PessoaDTO pessoaDTO = pessoaService.getPessoaById(id);
        return new ResponseEntity<>(pessoaDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> getAllPessoas() {
        List<PessoaDTO> pessoas = pessoaService.getAllPessoas();
        return new ResponseEntity<>(pessoas, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> updatePessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        PessoaDTO updatedPessoa = pessoaService.updatePessoa(id, pessoaDTO);
        return new ResponseEntity<>(updatedPessoa, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePessoa(@PathVariable Long id) {
        pessoaService.deletePessoa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}