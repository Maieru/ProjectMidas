package br.com.fesa.projectmidas.dataaccessobject;

import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.Agencia;
import br.com.fesa.projectmidas.model.CartaoCredito;
import br.com.fesa.projectmidas.model.CarteiraInvestimento;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.negocio.DateHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaBancariaDAO implements GenericDAO<ContaBancaria> {

    private String nomeTabela = "MIDAS.TBCONTABANCO";

    @Override
    public List<ContaBancaria> listar() throws PersistenciaException {
        List<ContaBancaria> contas = new ArrayList();
        String sql = String.format("SELECT * FROM %s", nomeTabela);
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                contas.add(montaObjeto(result));
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return contas;
    }

    @Override
    public void inserir(ContaBancaria conta) throws PersistenciaException {
        String sql = String.format("insert into %s (CORRENTINTA,\n"
                + "    CPF,\n"
                + "    NUMEROAGENCIA,\n"
                + "    SALDO,\n"
                + "    SENHA)\n"
                + "values values(?, ?, ?, ?, ?);", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, conta.getCorrentista());
            pStatement.setString(2, conta.getCPF());
            pStatement.setInt(3, conta.getAgencia().getCodigo());
            pStatement.setDouble(4, conta.getSaldo());
            pStatement.setString(5, conta.getSenha());

            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível carregar o driver de conexão com a base de dados");
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Erro ao enviar o comando para a base de dados");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void alterar(ContaBancaria conta) throws PersistenciaException {
        String sql = String.format("Update %s SET("
                + "    CORRENTINTA=?,\n"
                + "    CPF=?,\n"
                + "    NUMEROAGENCIA=?,\n"
                + "    SALDO=?,\n"
                + "    SENHA=?)\n"
                + "    Where NUMEROCONTA=?", nomeTabela);
                
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, conta.getCorrentista());
            pStatement.setString(2, conta.getCPF());
            pStatement.setInt(3, conta.getAgencia().getCodigo());
            pStatement.setDouble(4, conta.getSaldo());
            pStatement.setString(5, conta.getSenha());
            pStatement.setInt(6, conta.getNumero());

            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover(ContaBancaria conta) throws PersistenciaException {
        String sql = String.format("DELETE FROM %s WHERE Codigo = ?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, conta.getNumero());

            pStatement.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível conectar à base de dados!");
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Não foi possível enviar o comando para a base de dados!");
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ContaBancaria listarPorId(ContaBancaria conta) throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s WHERE Codigo = ?", nomeTabela);
        Connection connection = null;

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, conta.getNumero());

            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                conta = montaObjeto(result);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conta;
    }

    private ContaBancaria montaObjeto(ResultSet result) throws PersistenciaException, SQLException {
        Agencia agencia = new AgenciaDAO().listarPorId(new Agencia(result.getInt("NumeroAgencia")));
        List<CartaoCredito> cartoes = new CartaoCreditoDAO().listarPorConta(new ContaBancaria(result.getInt("NumeroConta")));
        CarteiraInvestimento carteira = new CarteiraInvestimentoDAO().listarPorConta(new ContaBancaria(result.getInt("NumeroConta")));

        return new ContaBancaria(result.getInt("NumeroConta"),
                agencia,
                result.getString("Senha"),
                result.getDouble("Saldo"),
                result.getString("Correntista"),
                result.getString("CPF"),
                cartoes, carteira);
    }
}