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

    private void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    public void modificaSaldo(double valorASerAdicionado){
        this.saldo += valorASerAdicionado;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }
    
    public void modificaRendimento (Investimento investimento){
        double saldoOriginal = this.getSaldo();
        double rendimentoOriginal = this.getRendimento();
        double totalInvestido = saldoOriginal + investimento.getValorInvestido();
        
        this.setRendimento((saldoOriginal / totalInvestido) * rendimentoOriginal + (investimento.getValorInvestido() / totalInvestido) * investimento.getTipo().getRendimentos());
    }
}