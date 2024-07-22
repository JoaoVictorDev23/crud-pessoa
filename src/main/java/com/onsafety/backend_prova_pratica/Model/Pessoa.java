package com.onsafety.backend_prova_pratica.Model;


import com.onsafety.backend_prova_pratica.DTO.PessoaDTO;
import com.onsafety.backend_prova_pratica.Util.CPF;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entidade Pessoa mapeada para a tabela 'pessoa'.
 */
@Data
@Entity
@Table(name = "pessoa")
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String nome;

    @CPF(message = "CPF inválido")
    private String cpf;

    private LocalDate dataNascimento;
    private String email;

    public Pessoa(PessoaDTO pessoaDTO) {
        this.nome = pessoaDTO.nome();
        this.cpf = pessoaDTO.cpf();
        this.dataNascimento = pessoaDTO.dataNascimento();
        this.email = pessoaDTO.email();
    }
}