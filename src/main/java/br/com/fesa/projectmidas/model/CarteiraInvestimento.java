package br.com.fesa.projectmidas.model;

public class CarteiraInvestimento {
    private int codigo;
    private ContaBancaria conta;
    private double saldo;
    private double rendimento;

    public CarteiraInvestimento() {
    }

    public CarteiraInvestimento(int codigo, ContaBancaria conta, double saldo, double rendimento) {
        this.codigo = codigo;
        this.conta = conta;
        this.saldo = saldo;
        this.rendimento = rendimento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }
}
