package com.onsafety.backend_prova_pratica.DTO;

import com.onsafety.backend_prova_pratica.Model.Pessoa;
import com.onsafety.backend_prova_pratica.Util.CPF;

import java.time.LocalDate;

public record PessoaDTO(
         Long id,
         String nome,
         String cpf,
         LocalDate dataNascimento,
         String email) {


    public PessoaDTO(Pessoa pessoa) {

        this(pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getDataNascimento(), pessoa.getEmail());

    }
}
