package br.com.fesa.projectmidas.model;

import java.util.HashMap;
import java.util.Map;

public enum TipoTransacao {
    DEPOSITO(0, "Deposito"),
    SAQUE(1, "Saque"),
    TRANSFERENCIA_PIX(2, "Transferência por PIX"),
    TRANSFERENCIA_TED(3, "Transferência por TED"),
    TRANSFERENCIA_DOC(4, "Transferência por DOC"),
    PAGAMENTO_BOLETO(5, "Pagamento por Boleto"),
    PAGAMENTO_PIX(6, "Pagamento por PIX"),
    INVESTIMENTO(7, "Investimento"),
    RENDIMENTO_INVESTIMENTO(8, "Rendimentos de Investimento");
    
    private Integer codigo;
    private String descricao;
    
    private static Map<Integer, TipoTransacao> mapa = new HashMap<Integer, TipoTransacao>();
    
    static {
        for (TipoTransacao tipoTransacao : TipoTransacao.values()){
            mapa.put(tipoTransacao.codigo, tipoTransacao);
        }
    }
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
    
    public static TipoTransacao getById(Integer id){
        return mapa.get(id);
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}
