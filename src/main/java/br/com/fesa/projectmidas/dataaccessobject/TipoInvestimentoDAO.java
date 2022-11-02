package br.com.fesa.projectmidas.dataaccessobject;

import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.TipoInvestimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TipoInvestimentoDAO implements GenericDAO<TipoInvestimento> {

    private String nomeTabela = "MIDAS.TBTIPOINVESTIMENTO";

    @Override
    public List<TipoInvestimento> listar() throws PersistenciaException {
        List<TipoInvestimento> tipoInvestimentos = new ArrayList();
        String sql = String.format("SELECT * FROM %s", nomeTabela);
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                tipoInvestimentos.add(montaObjeto(result));
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
        return tipoInvestimentos;
    }

    @Override
    public void inserir(TipoInvestimento investimento) throws PersistenciaException {
        String sql = String.format("INSERT INTO %s (NOME, RENDIMENTO, VALORMINIMO, SIGLA) VALUES (?, ?, ?, ?)", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, investimento.getNome());
            pStatement.setString(2, Double.toString(investimento.getRendimentos()));
            pStatement.setString(3, Double.toString(investimento.getValorMinimo()));
            pStatement.setString(4, investimento.getSigla());

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
    public void alterar(TipoInvestimento investimento) throws PersistenciaException {
        String sql = String.format("UPDATE %s SET NOME=?, RENDIMENTO=?, VALORMINIMO=?, SIGLA=? WHERE CODIGO = ?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, investimento.getNome());
            pStatement.setString(2, Double.toString(investimento.getRendimentos()));
            pStatement.setString(3, Double.toString(investimento.getValorMinimo()));
            pStatement.setString(4, investimento.getSigla());
            pStatement.setString(5, Integer.toString(investimento.getCodigo()));

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
    public void remover(TipoInvestimento investimento) throws PersistenciaException {
        String sql = String.format("DELETE FROM %s WHERE CODIGO = ?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, investimento.getCodigo());

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
    public TipoInvestimento listarPorId(TipoInvestimento investimento) throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s WHERE CODIGO = ?", nomeTabela);
        Connection connection = null;
        
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            
            pStatement.setLong(1, investimento.getCodigo());
            
            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                investimento = montaObjeto(result);
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
        return investimento;
    }
    
    private TipoInvestimento montaObjeto(ResultSet result) throws SQLException{
        return new TipoInvestimento(result.getInt("CODIGO"),
                                    result.getString("NOME"),
                                    result.getString("SIGLA"),
                                    result.getDouble("RENDIMENTO"),
                                    result.getDouble("VALORMINIMO"));
    }
}
