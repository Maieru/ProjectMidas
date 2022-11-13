package br.com.fesa.projectmidas.model;

import java.util.HashMap;
import java.util.Map;

public enum Meses {
    JANEIRO(1, "Janeiro"),
    FEVEREIRO(2, "Fevereiro"),
    MARCO(3, "Março"),
    ABRIL(4, "Abril"),
    MAIO(5, "Maio"),
    JUNHO(6, "Junho"),
    JULHO(7, "Julho"),
    AGOSTO(8, "Agosto"),
    SETEMBRO(9, "Setembro"),
    OUTUBRO(10, "Outubro"),
    NOVEMBRO(11, "Novembro"),
    DEZEMBRO(12, "Dezembro");
    
    private Integer codigo;
    private String descricao;
    
    private static Map<Integer, Meses> mapa = new HashMap<Integer, Meses>();
    
    static {
        for (Meses tipoTransacao : Meses.values()){
            mapa.put(tipoTransacao.codigo, tipoTransacao);
        }
    }
    
    Meses(Integer codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public Integer getCodigo(){
        return this.codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static Meses getById(Integer id){
        return mapa.get(id);
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
}
