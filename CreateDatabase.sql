CREATE SCHEMA MIDAS

CREATE TABLE MIDAS.tbBanco (
    NumeroConta     BIGINT          NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Agencia         INTEGER         NOT NULL,
    Saldo           DOUBLE          NOT NULL,
    NomeDoDono      VARCHAR(MAX)    NOT NULL,
    CPFDoDono       VARCHAR(MAX)    NOT NULL
    PRIMARY KEY (Agencia, NumeroConta)
);