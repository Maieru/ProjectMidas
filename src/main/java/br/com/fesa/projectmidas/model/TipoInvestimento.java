package br.com.fesa.projectmidas.model;

public class TipoInvestimento {
    private Integer codigo;
    private String nome;
    private String sigla;
    private double rendimentos;
    private double valorMinimo;

    public TipoInvestimento() {
    }

    public TipoInvestimento(Integer codigo, String nome, String sigla, double rendimentos, double valorMinimo) {
        this.codigo = codigo;
        this.nome = nome;
        this.sigla = sigla;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
