package br.com.fesa.projectmidas.model;

public class ContaBancaria {
    private Integer numeroConta;
    private Integer agencia;
    private String senha;
    private double saldo;
    private String nomeDono;
    private String CPFDono;
    private double limiteCredito;
    private double creditoDisponivel;

    public ContaBancaria() {
    }

    public ContaBancaria(Integer numeroConta, Integer agencia, String senha, double saldo, String nomeDono, String CPFDono, double limiteCredito, double creditoDisponivel) {
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.senha = senha;
        this.saldo = saldo;
        this.nomeDono = nomeDono;
        this.CPFDono = CPFDono;
        this.limiteCredito = limiteCredito;
        this.creditoDisponivel = creditoDisponivel;
    }

    public Integer getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Integer numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
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

    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getCPFDono() {
        return CPFDono;
    }

    public void setCPFDono(String CPFDono) {
        this.CPFDono = CPFDono;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public double getCreditoDisponivel() {
        return creditoDisponivel;
    }

    public void setCreditoDisponivel(double creditoDisponivel) {
        this.creditoDisponivel = creditoDisponivel;
    }
    
    public void modificaSaldo(double valorASerAdicionado){
        this.saldo += valorASerAdicionado;
    }
}
