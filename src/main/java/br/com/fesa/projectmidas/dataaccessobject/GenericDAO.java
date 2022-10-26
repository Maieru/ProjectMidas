package br.com.fesa.projectmidas.dataaccessobject;

import br.com.fesa.projectmidas.exception.PersistenciaException;
import java.io.Serializable;
import java.util.List;

public interface GenericDAO<E> extends Serializable {
    
    public void inserir(E objeto) throws PersistenciaException;
    public void alterar(E objeto) throws PersistenciaException;
    public void remover(E objeto) throws PersistenciaException;
    public List<E> listar() throws PersistenciaException;
    public E listarPorId() throws PersistenciaException;
}
