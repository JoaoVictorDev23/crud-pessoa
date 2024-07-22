package com.onsafety.backend_prova_pratica.Services;

import com.onsafety.backend_prova_pratica.DTO.PessoaDTO;

import java.util.List;

public interface PessoaService {
    PessoaDTO createPessoa(PessoaDTO pessoaDTO);
    PessoaDTO getPessoaById(Long id);
    List<PessoaDTO> getAllPessoas();
    PessoaDTO updatePessoa(Long id, PessoaDTO pessoaDTO);
    void deletePessoa(Long id);
}