package br.com.fesa.projectmidas.dataaccessobject;

import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.CarteiraInvestimento;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.Investimento;
import br.com.fesa.projectmidas.model.TipoInvestimento;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import br.com.fesa.projectmidas.negocio.DateHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransacaoDAO implements GenericDAO<Transacao> {

    private String nomeTabela = "MIDAS.TBTRANSACAO";

    @Override
    public List<Transacao> listar() throws PersistenciaException {
        List<Transacao> transacoes = new ArrayList();
        String sql = String.format("SELECT * FROM %s", nomeTabela);
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                transacoes.add(montaObjeto(result));
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
        return transacoes;
    }

    @Override
    public void inserir(Transacao transacao) throws PersistenciaException {
        String sql = String.format("insert into %s (CONTADESTINO,\n"
                + "    CONTAORIGEM,\n"
                + "    DATADATRANSACAO,\n"
                + "    DESCRICAO,\n"
                + "    PAGOEMCREDITO,\n"
                + "    TIPOTRANSACAO,\n"
                + "    VALOR)\n"
                + "values(?, ?, ?, ?, ?, ?, ?)", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            if (transacao.getDestino() != null) {
                pStatement.setInt(1, transacao.getDestino().getNumero());
            }else{
                pStatement.setNull(1, Types.INTEGER);
            }
            
            pStatement.setInt(2, transacao.getOrigem().getNumero());
            pStatement.setObject(3, DateHelper.asDate(transacao.getDataTransacao()));
            pStatement.setString(4, transacao.getDescricao());
            pStatement.setBoolean(5, transacao.isPagoComCredito());
            pStatement.setInt(6, transacao.getTipoTransacao().getCodigo());
            pStatement.setDouble(7, transacao.getValor());

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
    public void alterar(Transacao transacao) throws PersistenciaException {
        String sql = String.format("Update %s SET"
                + "    CONTADESTINO=?,\n"
                + "    CONTAORIGEM=?,\n"
                + "    DATADATRANSACAO=?,\n"
                + "    DESCRICAO=?,\n"
                + "    PAGOEMCREDITO=?,\n"
                + "    TIPOTRANSACAO=?,\n"
                + "    VALOR=?"
                + "    Where Codigo=?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, transacao.getDestino().getNumero());
            pStatement.setInt(2, transacao.getOrigem().getNumero());
            pStatement.setObject(3, transacao.getDataTransacao());
            pStatement.setString(4, transacao.getDescricao());
            pStatement.setBoolean(5, transacao.isPagoComCredito());
            pStatement.setInt(6, transacao.getTipoTransacao().getCodigo());
            pStatement.setDouble(7, transacao.getValor());
            pStatement.setInt(8, transacao.getCodigo());

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
    public void remover(Transacao transacao) throws PersistenciaException {
        String sql = String.format("DELETE FROM %s WHERE Codigo = ?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, transacao.getCodigo());

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
    public Transacao listarPorId(Transacao transacao) throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s WHERE Codigo = ?", nomeTabela);
        Connection connection = null;

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, transacao.getCodigo());

            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                transacao = montaObjeto(result);
                return transacao;
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

    private Transacao montaObjeto(ResultSet result) throws PersistenciaException, SQLException {
        ContaBancariaDAO daoContaBancaria = new ContaBancariaDAO();
        ContaBancaria origem = daoContaBancaria.listarPorId(new ContaBancaria(result.getInt("ContaOrigem")));
        ContaBancaria destino = daoContaBancaria.listarPorId(new ContaBancaria(result.getInt("ContaDestino")));

        return new Transacao(result.getInt("Codigo"),
                origem,
                destino,
                DateHelper.asLocalDateTime(result.getDate("DataDaTransacao")),
                result.getDouble("Valor"),
                result.getString("Descricao"),
                TipoTransacao.getById(result.getInt("TipoTransacao")),
                result.getBoolean("PagoEmCredito"));
    }
}
