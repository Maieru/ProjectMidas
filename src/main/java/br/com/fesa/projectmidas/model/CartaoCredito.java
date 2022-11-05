package br.com.fesa.projectmidas.model;

import java.time.LocalDate;

public class CartaoCredito {
    private int codigo;
    private String numero;
    private String senha;
    private double limiteCredito;
    private double creditoUtilizado;
    private LocalDate vencimento;
    private String CVV;
    private LocalDate dataFatura;
    private Bandeira bandeira;
    private Integer numeroConta;

    public CartaoCredito() {
    }

    public CartaoCredito(int codigo) {
        this.codigo = codigo;
    }

    public CartaoCredito(int codigo, String numero, String senha, double limiteCredito, double creditoUtilizado, LocalDate vencimento, String CVV, LocalDate dataFatura, Bandeira bandeira, Integer numeroConta) {
        this.codigo = codigo;
        this.numero = numero;
        this.senha = senha;
        this.limiteCredito = limiteCredito;
        this.creditoUtilizado = creditoUtilizado;
        this.vencimento = vencimento;
        this.CVV = CVV;
        this.dataFatura = dataFatura;
        this.bandeira = bandeira;
        this.numeroConta = numeroConta;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public double getCreditoUtilizado() {
        return creditoUtilizado;
    }

    public void setCreditoUtilizado(double creditoUtilizado) {
        this.creditoUtilizado = creditoUtilizado;
    }

    public LocalDate getVencimento() {
        return vencimento;
    }

    public void setVencimento(LocalDate vencimento) {
        this.vencimento = vencimento;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public LocalDate getDataFatura() {
        return dataFatura;
    }

    public void setDataFatura(LocalDate dataFatura) {
        this.dataFatura = dataFatura;
    }

    public Bandeira getBandeira() {
        return bandeira;
    }

    public void setBandeira(Bandeira bandeira) {
        this.bandeira = bandeira;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }
}
