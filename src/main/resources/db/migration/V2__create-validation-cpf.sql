DELIMITER $$

CREATE FUNCTION valida_cpf(cpf VARCHAR(11))
    RETURNS BOOLEAN
    DETERMINISTIC
BEGIN
    DECLARE cpf_num VARCHAR(11);
    DECLARE dv1 INT;
    DECLARE dv2 INT;
    DECLARE soma INT;
    DECLARE resto INT;

    IF cpf IS NULL THEN
        RETURN FALSE;
END IF;

    SET cpf_num = REPLACE(cpf, '.', '');
    SET cpf_num = REPLACE(cpf_num, '-', '');

    IF LENGTH(cpf_num) != 11 OR NOT cpf_num REGEXP '^[0-9]{11}$' THEN
        RETURN FALSE;
END IF;

    -- Calcula o primeiro dígito verificador
    SET soma = 0;
    SET dv1 = 0;
    SET resto = 0;

    SET soma = (SUBSTRING(cpf_num, 1, 1) * 10) +
               (SUBSTRING(cpf_num, 2, 1) * 9) +
               (SUBSTRING(cpf_num, 3, 1) * 8) +
               (SUBSTRING(cpf_num, 4, 1) * 7) +
               (SUBSTRING(cpf_num, 5, 1) * 6) +
               (SUBSTRING(cpf_num, 6, 1) * 5) +
               (SUBSTRING(cpf_num, 7, 1) * 4) +
               (SUBSTRING(cpf_num, 8, 1) * 3) +
               (SUBSTRING(cpf_num, 9, 1) * 2);

    SET resto = MOD(soma, 11);

    IF resto < 2 THEN
        SET dv1 = 0;
ELSE
        SET dv1 = 11 - resto;
END IF;

    -- Calcula o segundo dígito verificador
    SET soma = 0;
    SET dv2 = 0;
    SET resto = 0;

    SET soma = (SUBSTRING(cpf_num, 1, 1) * 11) +
               (SUBSTRING(cpf_num, 2, 1) * 10) +
               (SUBSTRING(cpf_num, 3, 1) * 9) +
               (SUBSTRING(cpf_num, 4, 1) * 8) +
               (SUBSTRING(cpf_num, 5, 1) * 7) +
               (SUBSTRING(cpf_num, 6, 1) * 6) +
               (SUBSTRING(cpf_num, 7, 1) * 5) +
               (SUBSTRING(cpf_num, 8, 1) * 4) +
               (SUBSTRING(cpf_num, 9, 1) * 3) +
               (dv1 * 2);

    SET resto = MOD(soma, 11);

    IF resto < 2 THEN
        SET dv2 = 0;
ELSE
        SET dv2 = 11 - resto;
END IF;

    -- Verifica se os dígitos calculados são iguais aos dígitos originais do CPF
    IF dv1 = SUBSTRING(cpf_num, 10, 1) AND dv2 = SUBSTRING(cpf_num, 11, 1) THEN
        RETURN TRUE;
ELSE
        RETURN FALSE;
END IF;
END$$

DELIMITER ;


DELIMITER ;
-- Trigger para validação ao inserir
DELIMITER $$

CREATE TRIGGER valida_cpf_trigger
    BEFORE INSERT ON pessoa
    FOR EACH ROW
BEGIN
    IF NOT valida_cpf(NEW.cpf) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'CPF inválido.';
END IF;
END$$

DELIMITER ;

-- Trigger para validação ao atualizar
DELIMITER $$

CREATE TRIGGER valida_cpf_trigger_update
    BEFORE UPDATE ON pessoa
    FOR EACH ROW
BEGIN
    IF NOT valida_cpf(NEW.cpf) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'CPF inválido.';
END IF;
END$$

DELIMITER ;