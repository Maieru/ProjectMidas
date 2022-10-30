package br.com.fesa.projectmidas.model;

public enum TipoTransacao {
    DEPOSITO(0),
    SAQUE(1),
    TRANSFERENCIA_PIX(2),
    TRANSFERENCIA_TED(3),
    TRANSFERENCIA_DOC(4),
    TRANSFERENCIA_BOLETO(5);
    
    private Integer codigo;
    
    TipoTransacao(Integer codigo){
        this.codigo = codigo;
    }
    
    public Integer getCodigo(){
        return this.codigo;
    }
}
