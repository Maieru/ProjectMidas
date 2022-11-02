package br.com.fesa.projectmidas.model;

public class Agencia {
    private int codigo;
    private String localizacao;

    public Agencia() {
    }

    public Agencia(int codigo, String localizacao) {
        this.codigo = codigo;
        this.localizacao = localizacao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }
}
