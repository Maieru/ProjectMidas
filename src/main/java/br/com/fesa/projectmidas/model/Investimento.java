package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class Investimento {
    private Integer codigo;
    private CarteiraInvestimento carteiraInvestimento;
    private LocalDateTime data;
    private double valorInvestido;

    public Investimento() {
    }

    public Investimento(Integer codigo, CarteiraInvestimento carteiraInvestimento, LocalDateTime data, double valorInvestido) {
        this.codigo = codigo;
        this.carteiraInvestimento = carteiraInvestimento;
        this.data = data;
        this.valorInvestido = valorInvestido;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public CarteiraInvestimento getCarteiraInvestimento() {
        return carteiraInvestimento;
    }

    public void setCarteiraInvestimento(CarteiraInvestimento carteiraInvestimento) {
        this.carteiraInvestimento = carteiraInvestimento;
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
