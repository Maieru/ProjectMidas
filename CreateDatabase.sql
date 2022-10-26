CREATE SCHEMA MIDAS;

CREATE TABLE MIDAS.tbContaBanco (
    NumeroConta     BIGINT          NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Agencia         INTEGER         NOT NULL,
    Saldo           DOUBLE          NOT NULL,
    NomeDoDono      VARCHAR(200)    NOT NULL,
    CPFDoDono       VARCHAR(15)     NOT NULL,
    PRIMARY KEY (Agencia, NumeroConta)
);
GO;

CREATE TABLE MIDAS.tbTransferencia (
    Codigo          BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ContaOrigem     BIGINT          NOT NULL,
    ContaDestino    BIGINT          NOT NULL,
    DataDaTransacao DATE            NOT NULL,
    Valor           DOUBLE          NOT NULL,
    TipoTransacao   INTEGER         NOT NULL
);