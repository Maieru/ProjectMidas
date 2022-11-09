package br.com.fesa.projectmidas.negocio;

import br.com.fesa.projectmidas.dataaccessobject.AgenciaDAO;
import br.com.fesa.projectmidas.exception.ObjetoInvalidoException;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.Agencia;

public class AgenciaNegocio {

    private Agencia agencia = new Agencia();

    public AgenciaNegocio() {
    }

    public AgenciaNegocio(Agencia agencia) {
        this.agencia = agencia;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public boolean validaESalvaAgencia(boolean edicao) throws ObjetoInvalidoException, PersistenciaException {
        if (this.agencia.getLocalizacao() == null || this.agencia.getLocalizacao().trim().isEmpty()) {
            throw new ObjetoInvalidoException("Localização informada é inválido!");
        }

        AgenciaDAO dao = new AgenciaDAO();

        if (edicao) {
            dao.alterar(agencia);
        } else {
            if (dao.listarPorId(this.agencia) != null) {
                throw new ObjetoInvalidoException("Já existe uma agencia com o código informado!");
            }
            
            dao.inserir(agencia);
        }

        return true;
    }
}
