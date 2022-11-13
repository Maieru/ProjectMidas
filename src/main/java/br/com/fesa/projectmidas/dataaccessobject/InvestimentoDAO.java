package br.com.fesa.projectmidas.dataaccessobject;

import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.CarteiraInvestimento;
import br.com.fesa.projectmidas.model.Investimento;
import br.com.fesa.projectmidas.model.TipoInvestimento;
import br.com.fesa.projectmidas.negocio.DateHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvestimentoDAO implements GenericDAO<Investimento> {

    private String nomeTabela = "MIDAS.TBINVESTIMENTO";

    @Override
    public List<Investimento> listar() throws PersistenciaException {
        List<Investimento> investimentos = new ArrayList();
        String sql = String.format("SELECT * FROM %s", nomeTabela);
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                investimentos.add(montaObjeto(result));
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
        return investimentos;
    }

    @Override
    public void inserir(Investimento investimento) throws PersistenciaException {
        String sql = String.format("insert into %s (DATA,\n"
                + "    CODIGOCARTEIRA,\n"
                + "    TIPOINVESTIMENTO,\n"
                + "    VALORINVESTIDO)\n"
                + "values(?, ?, ?, ?)", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setObject(1, DateHelper.asDate(investimento.getData()));
            pStatement.setInt(2, investimento.getCarteira().getCodigo());
            pStatement.setInt(3, investimento.getTipo().getCodigo());
            pStatement.setDouble(4, investimento.getValorInvestido());

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
    public void alterar(Investimento investimento) throws PersistenciaException {
        String sql = String.format("Update %s SET"
                + "    DATA=?,\n"
                + "    CODIGOCARTEIRA=?,\n"
                + "    TIPOINVESTIMENTO=?,\n"
                + "    VALORINVESTIDO=?\n"
                + "    Where CODIGO=?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setObject(1, DateHelper.asDate(investimento.getData()));
            pStatement.setInt(2, investimento.getCarteira().getCodigo());
            pStatement.setInt(3, investimento.getTipo().getCodigo());
            pStatement.setDouble(4, investimento.getValorInvestido());
            pStatement.setInt(5, investimento.getCodigo());
            
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
    public void remover(Investimento investimento) throws PersistenciaException {
        String sql = String.format("DELETE FROM %s WHERE Codigo = ?", nomeTabela);

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
    public Investimento listarPorId(Investimento investimento) throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s WHERE Codigo = ?", nomeTabela);
        Connection connection = null;

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, investimento.getCodigo());

            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                investimento = montaObjeto(result);
                return investimento;
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

    public List<Investimento> listarPorCarteira(CarteiraInvestimento carteira) throws PersistenciaException {
        List<Investimento> investimentos = new ArrayList();
        String sql = String.format("SELECT * FROM %s WHERE CodigoCarteira=?", nomeTabela);
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            
            pStatement.setInt(1, carteira.getCodigo());
            
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                investimentos.add(montaObjeto(result));
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
        return investimentos;
    }
    
    private Investimento montaObjeto(ResultSet result) throws PersistenciaException, SQLException {
        CarteiraInvestimento cartaoCredito = new CarteiraInvestimentoDAO().listarPorId(new CarteiraInvestimento(result.getInt("CodigoCarteira")));
        TipoInvestimento tipoInvestimento = new TipoInvestimentoDAO().listarPorId(new TipoInvestimento(result.getInt("TipoInvestimento")));

        return new Investimento(result.getInt("Codigo"),
                cartaoCredito,
                tipoInvestimento,
                DateHelper.asLocalDateTime(result.getDate("Data")),
                result.getDouble("ValorInvestido"));
    }
}
