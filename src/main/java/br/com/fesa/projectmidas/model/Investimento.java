package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class Investimento {
    private Integer codigo;
    private CarteiraInvestimento carteira;
    private TipoInvestimento tipo;
    private LocalDateTime data;
    private double valorInvestido;

    public Investimento() {
    }

    public Investimento(Integer codigo, CarteiraInvestimento carteira, TipoInvestimento tipo, LocalDateTime data, double valorInvestido) {
        this.codigo = codigo;
        this.carteira = carteira;
        this.tipo = tipo;
        this.data = data;
        this.valorInvestido = valorInvestido;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public CarteiraInvestimento getCarteira() {
        return carteira;
    }

    public void setCarteira(CarteiraInvestimento carteira) {
        this.carteira = carteira;
    }

    public TipoInvestimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoInvestimento tipo) {
        this.tipo = tipo;
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
