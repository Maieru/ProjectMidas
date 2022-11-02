package br.com.fesa.projectmidas.negocio;

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

    public boolean validaESalvaContaBancaria() throws Exception {
        throw new Exception("Ainda não implementado");
    }
}
