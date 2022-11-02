package br.com.fesa.projectmidas.model;

public enum Bandeira {
    VISA(0, "Visa"),
    MASTERCARD(1, "Mastercard"),
    ELO(2, "Elo"),
    AMERICAN_EXPRESS(3, "American Express"),
    HIPERCARD(4, "Hipercard");
    
    private Integer codigo;
    private String descricao;
    
    Bandeira(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
