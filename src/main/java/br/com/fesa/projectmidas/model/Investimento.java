package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class Investimento {
    private Integer codigo;
    private Integer numeroConta;
    private Integer tipoInvestimento;
    private LocalDateTime data;
    private double valorInvestido;

    public Investimento() {
    }

    public Investimento(Integer codigo, Integer numeroConta, Integer tipoInvestimento, LocalDateTime data, double valorInvestido) {
        this.codigo = codigo;
        this.numeroConta = numeroConta;
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

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(Integer tipoInvestimento) {
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
