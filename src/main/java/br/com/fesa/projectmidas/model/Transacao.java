package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class Transacao {
    private Integer codigo;
    private Integer contaOrigem;
    private Integer contaDestino;
    private LocalDateTime dataTransacao;
    private double valor;
    private String descricao;
    private TipoTransacao tipoTransacao;
    private boolean pagoComCredito;

    public Transacao() {
    }

    public Transacao(Integer codigo, Integer contaOrigem, Integer contaDestino, LocalDateTime dataTransacao, double valor, String descricao, TipoTransacao tipoTransacao, boolean pagoComCredito) {
        this.codigo = codigo;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.dataTransacao = dataTransacao;
        this.valor = valor;
        this.descricao = descricao;
        this.tipoTransacao = tipoTransacao;
        this.pagoComCredito = pagoComCredito;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Integer contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Integer getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Integer contaDestino) {
        this.contaDestino = contaDestino;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public boolean isPagoComCredito() {
        return pagoComCredito;
    }

    public void setPagoComCredito(boolean pagoComCredito) {
        this.pagoComCredito = pagoComCredito;
    }
}
