package br.com.fesa.projectmidas.dataaccessobject;

import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.Bandeira;
import br.com.fesa.projectmidas.model.CartaoCredito;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.negocio.DateHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CartaoCreditoDAO implements GenericDAO<CartaoCredito> {

    private String nomeTabela = "MIDAS.TBCARTAOCREDITO";

    @Override
    public List<CartaoCredito> listar() throws PersistenciaException {
        List<CartaoCredito> cartoes = new ArrayList();
        String sql = String.format("SELECT * FROM %s", nomeTabela);
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                cartoes.add(montaObjeto(result));
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
        return cartoes;
    }

    @Override
    public void inserir(CartaoCredito cartao) throws PersistenciaException {
        String sql = String.format("INSERT INTO %s (NumeroConta, NumeroCartao, Senha, LimiteCredito, CreditoUtilizado, Vencimento, CVV, DataFatura, Bandeira) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, cartao.getNumeroConta());
            pStatement.setString(2, cartao.getNumero());
            pStatement.setString(3, cartao.getSenha());
            pStatement.setDouble(4, cartao.getLimiteCredito());
            pStatement.setDouble(5, cartao.getCreditoUtilizado());
            pStatement.setObject(6, cartao.getVencimento());
            pStatement.setString(7, cartao.getCVV());
            pStatement.setObject(8, cartao.getDataFatura());
            pStatement.setInt(9, cartao.getBandeira().getCodigo());

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
    public void alterar(CartaoCredito cartao) throws PersistenciaException {
        String sql = String.format("UPDATE %s SET NumeroConta=?, NumeroCartao=?, Senha=?, LimiteCredito=?, CreditoUtilizado=?, Vencimento=?, CVV=?, DataFatura=?, Bandeira=? WHERE Codigo=?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, cartao.getNumeroConta());
            pStatement.setString(2, cartao.getNumero());
            pStatement.setString(3, cartao.getSenha());
            pStatement.setDouble(4, cartao.getLimiteCredito());
            pStatement.setDouble(5, cartao.getCreditoUtilizado());
            pStatement.setObject(6, cartao.getVencimento());
            pStatement.setString(7, cartao.getCVV());
            pStatement.setObject(8, cartao.getDataFatura());
            pStatement.setInt(9, cartao.getBandeira().getCodigo());
            pStatement.setInt(10, cartao.getCodigo());
            
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
    public void remover(CartaoCredito cartao) throws PersistenciaException {
        String sql = String.format("DELETE FROM %s WHERE Codigo = ?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, cartao.getCodigo());

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
    public CartaoCredito listarPorId(CartaoCredito cartao) throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s WHERE Codigo = ?", nomeTabela);
        Connection connection = null;

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, cartao.getCodigo());

            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                cartao = montaObjeto(result);
                return cartao;
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
        return null;
    }
    
    public List<CartaoCredito> listarPorConta(ContaBancaria conta) throws PersistenciaException {
        List<CartaoCredito> cartoes = new ArrayList();
        String sql = String.format("SELECT * FROM %s WHERE NumeroConta = ?", nomeTabela);
        Connection connection = null;

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, conta.getNumero());

            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                cartoes.add(montaObjeto(result));
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
        return cartoes;
    }

    private CartaoCredito montaObjeto(ResultSet result) throws SQLException {
        return new CartaoCredito(result.getInt("Codigo"),
                result.getString("NumeroCartao"),
                result.getString("Senha"),
                result.getDouble("LimiteCredito"),
                result.getDouble("CreditoUtilizado"),
                DateHelper.asLocalDate(result.getDate("Vencimento")),
                result.getString("CVV"),
                DateHelper.asLocalDate(result.getDate("DataFatura")),
                Bandeira.getById(result.getInt("Bandeira")),
                result.getInt("NumeroConta"));
    }
}
