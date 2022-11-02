package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class Fatura {
    private Integer codigo;
    private CartaoCredito cartao;
    private double valor;
    private LocalDateTime dataInicioPeriodo;
    private LocalDateTime dataFimPeriodo;
    private LocalDateTime dataVencimento;
    private double juros;
    private boolean pago;

    public Fatura() {
    }

    public Fatura(Integer codigo, CartaoCredito cartao, double valor, LocalDateTime dataInicioPeriodo, LocalDateTime dataFimPeriodo, LocalDateTime dataVencimento, double juros, boolean pago) {
        this.codigo = codigo;
        this.cartao = cartao;
        this.valor = valor;
        this.dataInicioPeriodo = dataInicioPeriodo;
        this.dataFimPeriodo = dataFimPeriodo;
        this.dataVencimento = dataVencimento;
        this.juros = juros;
        this.pago = pago;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public CartaoCredito getCartao() {
        return cartao;
    }

    public void setCartao(CartaoCredito cartao) {
        this.cartao = cartao;
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

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }
}
