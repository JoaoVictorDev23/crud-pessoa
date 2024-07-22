package com.onsafety.backend_prova_pratica.Util;
import com.onsafety.backend_prova_pratica.Exception.InvalidCPFException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 * Validador customizado para CPF.
 */
public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public void initialize(CPF constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext context) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            return false;
        }

        int[] cpfArray = new int[11];
        for (int i = 0; i < 11; i++) {
            cpfArray[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        // Primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += cpfArray[i] * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador == 10 || primeiroDigitoVerificador == 11) {
            primeiroDigitoVerificador = 0;
        }

        // Segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += cpfArray[i] * (11 - i);
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador == 10 || segundoDigitoVerificador == 11) {
            segundoDigitoVerificador = 0;
        }

        return cpfArray[9] == primeiroDigitoVerificador && cpfArray[10] == segundoDigitoVerificador;
    }
}
