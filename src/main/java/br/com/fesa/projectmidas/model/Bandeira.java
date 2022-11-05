package br.com.fesa.projectmidas.model;

import java.util.HashMap;
import java.util.Map;

public enum Bandeira {
    VISA(0, "Visa"),
    MASTERCARD(1, "Mastercard"),
    ELO(2, "Elo"),
    AMERICAN_EXPRESS(3, "American Express"),
    HIPERCARD(4, "Hipercard");
    
    private Integer codigo;
    private String descricao;
    
    private static Map<Integer, Bandeira> mapa = new HashMap<Integer, Bandeira>();
    
    static {
        for (Bandeira bandeira : Bandeira.values()){
            mapa.put(bandeira.codigo, bandeira);
        }
    }
    
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
    
    public static Bandeira getById(Integer id){
        return mapa.get(id);
    }
}