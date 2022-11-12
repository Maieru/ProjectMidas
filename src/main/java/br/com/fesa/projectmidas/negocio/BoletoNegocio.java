package br.com.fesa.projectmidas.negocio;

import br.com.fesa.projectmidas.model.ContaBancaria;
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
}
