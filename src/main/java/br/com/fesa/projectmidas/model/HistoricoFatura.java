package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class HistoricoFatura {
    private Integer codigo;
    private Integer numeroConta;
    private double valor;
    private LocalDateTime dataInicioPeriodo;
    private LocalDateTime dataFimPeriodo;
    private LocalDateTime dataVencimento;
    private double juros;

    public HistoricoFatura() {
    }

    public HistoricoFatura(Integer codigo, Integer numeroConta, double valor, LocalDateTime dataInicioPeriodo, LocalDateTime dataFimPeriodo, LocalDateTime dataVencimento, double juros) {
        this.codigo = codigo;
        this.numeroConta = numeroConta;
        this.valor = valor;
        this.dataInicioPeriodo = dataInicioPeriodo;
        this.dataFimPeriodo = dataFimPeriodo;
        this.dataVencimento = dataVencimento;
        this.juros = juros;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataInicioPeriodo() {
        return dataInicioPeriodo;
    }

    public void setDataInicioPeriodo(LocalDateTime dataInicioPeriodo) {
        this.dataInicioPeriodo = dataInicioPeriodo;
    }

    public LocalDateTime getDataFimPeriodo() {
        return dataFimPeriodo;
    }

    public void setDataFimPeriodo(LocalDateTime dataFimPeriodo) {
        this.dataFimPeriodo = dataFimPeriodo;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }
}
