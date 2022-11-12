package br.com.fesa.projectmidas.negocio;

public final class Constantes {

    private Constantes() {
    }

    public static final String CPFRegex = "([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})";
    
    // Chaves de User Date
    public static final String tipoTransacaoSendoRealizado = "tipoTransacaoSendoRealizado";
    public static final String chaveUserDateAgencia = "agenciaSendoEditada";
    public static final String chaveTransferenciaSendoRealizada = "transferenciaSendoRealizada";
    public static final String chavePagamentoSendoRealizada = "pagamentoSendoRealizada";
    public static final String chaveValorDeposito = "valorDeposito";
}
