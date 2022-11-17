package br.com.fesa.projectmidas.negocio;

import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.dataaccessobject.TransacaoDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.CarteiraInvestimento;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;

public final class RendimentoNegocio {

    private final static ResourceBundle BUNDLE = ResourceBundle.getBundle("rendimento", new Locale("pt", "BR"));
    private final static double rendimentoSELIC = Double.parseDouble(BUNDLE.getString("rendimentoSelic"));

    private RendimentoNegocio() {
    }

    public static void calculaRendimentos(ContaBancaria conta) throws PersistenciaException {
        ContaBancariaDAO contaDao = new ContaBancariaDAO();
        TransacaoDAO transacaoDao = new TransacaoDAO();

        if (conta.getSaldo() > 0) {
            double rendimentoSelic = conta.getSaldo() * rendimentoSELIC;
            conta.modificaSaldo(rendimentoSelic);
            
            Transacao transacao = new Transacao();
            transacao.setDescricao("REND SELIC");
            transacao.setOrigem(conta);
            transacao.setTipoTransacao(TipoTransacao.RENDIMENTO_SELIC);
            transacao.setValor(rendimentoSelic);
            transacao.setDataTransacao(LocalDateTime.now());

            transacaoDao.inserir(transacao);
        }

        CarteiraInvestimento carteira = conta.getCarteira();
        if (carteira != null) {
            double rendimentoDaCarteira = carteira.getRendimento() * carteira.getSaldo();
            conta.modificaSaldo(rendimentoDaCarteira);

            Transacao transacao = new Transacao();
            transacao.setDescricao("REND INV");
            transacao.setOrigem(conta);
            transacao.setTipoTransacao(TipoTransacao.RENDIMENTO_INVESTIMENTO);
            transacao.setValor(rendimentoDaCarteira);
            transacao.setDataTransacao(LocalDateTime.now());

            transacaoDao.inserir(transacao);
        }
        
        contaDao.alterar(conta);
    }
}
