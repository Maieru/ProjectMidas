package br.com.fesa.projectmidas.negocio;

import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import java.time.LocalDateTime;
import java.util.Random;

public final class BoletoNegocio {

    public static final Integer numeroCaracteresValor = 10;
    public static final Integer numeroCaracteresCodigoConta = 10;
    public static final Integer numeroCaracteresBoleto = 48;

    private BoletoNegocio() {
    }

    public static String geraCodigoBoleto(ContaBancaria conta, double valor) {
        String codigoBoleto = "";

        String valorFormatado = Integer.toString((int) (valor * 100));

        while (valorFormatado.length() < numeroCaracteresValor) {
            valorFormatado = "0" + valorFormatado;
        }

        codigoBoleto += valorFormatado;
        String numeroContaFormatado = Integer.toString(conta.getNumero());

        while (numeroContaFormatado.length() < numeroCaracteresCodigoConta) {
            numeroContaFormatado = "0" + numeroContaFormatado;
        }

        codigoBoleto += numeroContaFormatado;

        // Adiciona a seed para o resto dos digitos não se repetirem caso a aplicação seja reiniciada
        Random randomGenerator = new Random((LocalDateTime.now().getMinute() + LocalDateTime.now().getSecond() + LocalDateTime.now().getYear()) * LocalDateTime.now().getNano());

        while (codigoBoleto.length() <= numeroCaracteresBoleto) {
            codigoBoleto += Integer.toString(randomGenerator.nextInt(10));
        }

        return codigoBoleto;
    }

    public static Transacao leCodigoBoleto(String codigoBoleto) throws PersistenciaException {
        Transacao transacao = new Transacao();
        
        transacao.setTipoTransacao(TipoTransacao.PAGAMENTO_BOLETO);
        
        double valorBoleto = Double.parseDouble(codigoBoleto.substring(0, numeroCaracteresValor)) / 100;
        Integer numeroConta = Integer.parseInt(codigoBoleto.substring(numeroCaracteresValor + 1, numeroCaracteresCodigoConta + numeroCaracteresValor));
        
        ContaBancariaDAO dao = new ContaBancariaDAO();
        transacao.setDestino(dao.listarPorNumero(new ContaBancaria(numeroConta)));
        transacao.setValor(valorBoleto);
        
        return transacao;
    }
}
