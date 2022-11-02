CREATE SCHEMA MIDAS;

CREATE TABLE MIDAS.tbAgencia (
    NumeroAgencia       INT             NOT NULL PRIMARY KEY,
    Localizacao         VARCHAR(200)    NOT NULL
);

CREATE TABLE MIDAS.tbContaBanco (
    NumeroConta         BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NumeroAgencia       INTEGER         NOT NULL REFERENCES tbAgencia(NumeroAgencia),
    Senha               VARCHAR(128)    NOT NULL,
    Saldo               DOUBLE          NOT NULL DEFAULT 0,
    Correntinta         VARCHAR(200)    NOT NULL,
    CPF                 VARCHAR(15)     NOT NULL
);

CREATE TABLE MIDAS.tbCartaoCredito (
    NumeroCartao        BIGINT          NOT NULL PRIMARY KEY,
    NumeroConta         BIGINT          NOT NULL REFERENCES tbContaBanco(NumeroConta),
    Senha               VARCHAR(128)    NOT NULL,
    LimiteCredito       DOUBLE          NOT NULL,
    CreditoUtilizado    DOUBLE          NOT NULL,
    Vencimento          DATE            NOT NULL,
    CVV                 VARCHAR(15)     NOT NULL,
    DataFatura          DATE            NOT NULL,
    Bandeira            INTEGER         NOT NULL
);

CREATE TABLE MIDAS.tbFatura (
    Codigo              BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NumeroCartao        BIGINT          NOT NULL REFERENCES tbCartaoCredito(NumeroCartao),
    Valor               DOUBLE          NOT NULL,
    DataInicio          TIMESTAMP       NOT NULL,
    DataTermino         TIMESTAMP       NOT NULL,
    Vencimento          TIMESTAMP       NOT NULL,
    Juros               DOUBLE          NOT NULL,
    Pago                BOOLEAN         NOT NULL DEFAULT FALSE
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

CREATE TABLE MIDAS.tbCarteiraInvestimento (
    Codigo              BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NumeroConta         BIGINT          NOT NULL REFERENCES tbContaBanco(NumeroConta),
    SaldoNaCarteira     DOUBLE          NOT NULL DEFAULT 0,
    Rendimento          DOUBLE          NOT NULL DEFAULT 0
);

CREATE TABLE MIDAS.tbTipoInvestimento (
    Codigo              INT             NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Nome                VARCHAR(100)    NOT NULL,
    Rendimento          DOUBLE          NOT NULL,
    ValorMinimo         DOUBLE          NOT NULL,
    Sigla               VARCHAR(15)     NOT NULL
);

CREATE TABLE MIDAS.tbInvestimento (
    Codigo              BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    NumeroCarteira      BIGINT          NOT NULL REFERENCES tbCarteiraInvestimento(Codigo),
    TipoInvestimento    INT             NOT NULL REFERENCES tbTipoInvestimento(Codigo),
    Data                TIMESTAMP       NOT NULL,
    ValorInvestido      DOUBLE          NOT NULL
);
