package br.com.fesa.projectmidas.model;

public enum TipoTransacao {
    DEPOSITO(0, "Deposito"),
    SAQUE(1, "Saque"),
    TRANSFERENCIA_PIX(2, "Transferência por PIX"),
    TRANSFERENCIA_TED(3, "Transferência por TED"),
    TRANSFERENCIA_DOC(4, "Transferência por DOC"),
    TRANSFERENCIA_BOLETO(5, "Transferência por Boleto");
    
    private Integer codigo;
    private String descricao;
    
    TipoTransacao(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public Integer getCodigo(){
        return this.codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
