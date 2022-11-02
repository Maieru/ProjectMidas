package br.com.fesa.projectmidas.model;

public class CarteiraInvestimento {
    private int codigo;
    private double saldo;
    private double rendimento;

    public CarteiraInvestimento() {
    }

    public CarteiraInvestimento(int codigo, double saldo, double rendimento) {
        this.codigo = codigo;
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
}
