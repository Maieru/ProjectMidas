package br.com.fesa.projectmidas.model;

public class CarteiraInvestimento {
    private int codigo;
    private int contaBancaria;
    private double saldo;
    private double rendimento;

    public CarteiraInvestimento() {
    }

    public CarteiraInvestimento(int codigo) {
        this.codigo = codigo;
    }
    
    public CarteiraInvestimento(int codigo, int contaBancaria, double saldo, double rendimento) {
        this.codigo = codigo;
        this.contaBancaria = contaBancaria;
        this.saldo = saldo;
        this.rendimento = rendimento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(int contaBancaria) {
        this.contaBancaria = contaBancaria;
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
}
