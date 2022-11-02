package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class Investimento {
    private Integer codigo;
    private ContaBancaria conta;
    private TipoInvestimento tipoInvestimento;
    private LocalDateTime data;
    private double valorInvestido;

    public Investimento() {
    }

    public Investimento(Integer codigo, ContaBancaria conta, TipoInvestimento tipoInvestimento, LocalDateTime data, double valorInvestido) {
        this.codigo = codigo;
        this.conta = conta;
        this.tipoInvestimento = tipoInvestimento;
        this.data = data;
        this.valorInvestido = valorInvestido;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    public TipoInvestimento getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(TipoInvestimento tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }
}
