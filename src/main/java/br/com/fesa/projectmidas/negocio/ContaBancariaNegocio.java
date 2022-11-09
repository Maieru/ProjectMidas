package br.com.fesa.projectmidas.negocio;

import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.exception.ObjetoInvalidoException;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.ContaBancaria;

public class ContaBancariaNegocio {

    private ContaBancaria conta = new ContaBancaria();

    public ContaBancariaNegocio() {
    }

    public ContaBancariaNegocio(ContaBancaria conta) {
        this.conta = conta;
    }

    public ContaBancaria getConta() {
        return conta;
    }

    public void setConta(ContaBancaria conta) {
        this.conta = conta;
    }

    public boolean validaESalvaContaBancaria() throws ObjetoInvalidoException, PersistenciaException {
        if (!this.conta.getCPF().matches(Constantes.CPFRegex))
            throw new ObjetoInvalidoException("CPF informado é inválido!");
        
        if (this.conta.getCorrentista() == null || this.conta.getCorrentista().trim().isEmpty())
            throw new ObjetoInvalidoException("Correntista informado é inválido!");
        
        if (this.conta.getSenha()== null || this.conta.getSenha().trim().isEmpty())
            throw new ObjetoInvalidoException("Senha informado é inválido!");
        
        this.conta.encriptaSenha();
        
        ContaBancariaDAO dao = new ContaBancariaDAO();
        dao.inserir(conta);
        
        return true;
    }
    
    public ContaBancaria validaCredenciais() throws ObjetoInvalidoException, PersistenciaException {
        this.conta.encriptaSenha();
        
        ContaBancariaDAO dao = new ContaBancariaDAO();
        return dao.listarPorNumeroESenha(conta);
    }
}
