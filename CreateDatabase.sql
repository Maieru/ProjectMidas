CREATE SCHEMA MIDAS;

CREATE TABLE MIDAS.tbContaBanco (
    NumeroConta         BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Agencia             INTEGER         NOT NULL,
    Senha               VARCHAR(128)    NOT NULL,
    Saldo               DOUBLE          NOT NULL,
    NomeDoDono          VARCHAR(200)    NOT NULL,
    CPFDoDono           VARCHAR(15)     NOT NULL,
    LimiteCredito       DOUBLE          NOT NULL,
    CreditoDisponivel   DOUBLE          NOT NULL
);

CREATE TABLE MIDAS.tbTransacao (
    Codigo              BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ContaOrigem         BIGINT          NOT NULL REFERENCES tbContaBanco(NumeroConta),
    ContaDestino        BIGINT          REFERENCES tbContaBanco(NumeroConta),
    DataDaTransacao     TIMESTAMP       NOT NULL,
    Valor               DOUBLE          NOT NULL,
    Descricao           VARCHAR(100)    NOT NULL,
    TipoTransacao       INTEGER         NOT NULL,
    PagoEmCredito       BOOLEAN         NOT NULL
);

CREATE TABLE MIDAS.tbHistoricoFatura (
    Codigo              BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NumeroConta         BIGINT          NOT NULL REFERENCES tbContaBanco(NumeroConta),
    Valor               DOUBLE          NOT NULL,
    DataInicio          TIMESTAMP       NOT NULL,
    DataTermino         TIMESTAMP       NOT NULL,
    Vencimento          TIMESTAMP       NOT NULL,
    Juros               DOUBLE          NOT NULL
);

CREATE TABLE MIDAS.tbTipoInvestimento (
    Codigo              INT             NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Nome                VARCHAR(100)    NOT NULL,
    Rendimento          DOUBLE          NOT NULL,
    ValorMinimo         DOUBLE          NOT NULL
);

CREATE TABLE MIDAS.tbInvestimento (
    Codigo              BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NumeroConta         BIGINT          NOT NULL REFERENCES tbContaBanco(NumeroConta),
    TipoInvestimento    INT             NOT NULL REFERENCES tbTipoInvestimento(Codigo),
    Data                TIMESTAMP       NOT NULL,
    ValorInvestido      DOUBLE          NOT NULL
);