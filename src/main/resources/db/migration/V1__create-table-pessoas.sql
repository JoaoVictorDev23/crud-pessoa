CREATE TABLE pessoa (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255) NOT NULL,
                        cpf VARCHAR(11) NOT NULL UNIQUE,
                        data_nascimento DATE,
                        email VARCHAR(255)
);
