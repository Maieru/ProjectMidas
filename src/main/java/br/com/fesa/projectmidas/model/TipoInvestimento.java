package br.com.fesa.projectmidas.model;

public class TipoInvestimento {
    private Integer codigo;
    private String nome;
    private double rendimentos;
    private double valorMinimo;

    public TipoInvestimento() {
    }

    public TipoInvestimento(Integer codigo, String nome, double rendimentos, double valorMinimo) {
        this.codigo = codigo;
        this.nome = nome;
        this.rendimentos = rendimentos;
        this.valorMinimo = valorMinimo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getRendimentos() {
        return rendimentos;
    }

    public void setRendimentos(double rendimentos) {
        this.rendimentos = rendimentos;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }
}
