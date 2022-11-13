package br.com.fesa.projectmidas.negocio;

import br.com.fesa.projectmidas.dataaccessobject.CarteiraInvestimentoDAO;
import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.dataaccessobject.InvestimentoDAO;
import br.com.fesa.projectmidas.dataaccessobject.TransacaoDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.CarteiraInvestimento;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.Investimento;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import java.time.LocalDateTime;

public final class InvestimentoNegocio {

    private InvestimentoNegocio() {

    }
    
    public static void fazInvestimento(ContaBancaria conta, Investimento investimento) throws PersistenciaException{
        CarteiraInvestimento carteira = conta.getCarteira();
        
        conta.modificaSaldo(-investimento.getValorInvestido());
        carteira.modificaRendimento(investimento);
        carteira.modificaSaldo(investimento.getValorInvestido());
        
        ContaBancariaDAO contaDao = new ContaBancariaDAO();
        InvestimentoDAO investimentoDao = new InvestimentoDAO();
        CarteiraInvestimentoDAO carteiraDao = new CarteiraInvestimentoDAO();
        TransacaoDAO transacaoDao = new TransacaoDAO();
        
        Transacao transacao = new Transacao();
        transacao.setOrigem(conta);
        transacao.setDataTransacao(LocalDateTime.now());
        transacao.setDescricao("INV " + investimento.getTipo().getSigla());
        transacao.setTipoTransacao(TipoTransacao.INVESTIMENTO);
        transacao.setValor(investimento.getValorInvestido());
        
        investimento.setData(LocalDateTime.now());
        
        contaDao.alterar(conta);
        carteiraDao.alterar(carteira);
        investimentoDao.inserir(investimento);
        transacaoDao.inserir(transacao);
    }
}
