package br.com.fesa.projectmidas.model;

import java.util.List;

public class ContaBancaria {
    private Integer numero;
    private Agencia agencia;
    private String senha;
    private double saldo;
    private String correntista;
    private String CPF;
    private List<CartaoCredito> cartoes;

    public ContaBancaria() {
    }

    public ContaBancaria(Integer numero, Agencia agencia, String senha, double saldo, String correntista, String CPF, List<CartaoCredito> cartoes) {
        this.numero = numero;
        this.agencia = agencia;
        this.senha = senha;
        this.saldo = saldo;
        this.correntista = correntista;
        this.CPF = CPF;
        this.cartoes = cartoes;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public List<CartaoCredito> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoCredito> cartoes) {
        this.cartoes = cartoes;
    }
   
    public void modificaSaldo(double valorASerAdicionado){
        this.saldo += valorASerAdicionado;
    }
}
