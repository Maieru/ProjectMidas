package br.com.fesa.projectmidas.dataaccessobject;

import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.CartaoCredito;
import br.com.fesa.projectmidas.model.Fatura;
import br.com.fesa.projectmidas.negocio.DateHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FaturaDAO implements GenericDAO<Fatura> {

    private String nomeTabela = "MIDAS.TBFATURA";

    @Override
    public List<Fatura> listar() throws PersistenciaException {
        List<Fatura> faturas = new ArrayList();
        String sql = String.format("SELECT * FROM %s", nomeTabela);
        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet result = pStatement.executeQuery();
            while (result.next()) {
                faturas.add(montaObjeto(result));
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
        return faturas;
    }

    @Override
    public void inserir(Fatura fatura) throws PersistenciaException {
        String sql = String.format("insert into %s ( DATAINICIO,\n"
                + "    DATATERMINO,\n"
                + "    JUROS,\n"
                + "    CODIGOCARTAO,\n"
                + "    PAGO,\n"
                + "    VALOR,\n"
                + "    VENCIMENTO)\n"
                + "values (?, ?, ?, ?, ?, ?, ?);", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setObject(1, fatura.getDataInicioPeriodo());
            pStatement.setObject(2, fatura.getDataFimPeriodo());
            pStatement.setDouble(3, fatura.getJuros());
            pStatement.setInt(4, fatura.getCartao().getCodigo());
            pStatement.setBoolean(5, fatura.isPago());
            pStatement.setDouble(6, fatura.getValor());
            pStatement.setObject(7, fatura.getDataVencimento());

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
    public void alterar(Fatura fatura) throws PersistenciaException {
        String sql = String.format("Update %s SET("
                + "    DATAINICIO=?,\n"
                + "    DATATERMINO=?,\n"
                + "    JUROS=?,\n"
                + "    CODIGOCARTAO=?,\n"
                + "    PAGO=?,\n"
                + "    VALOR=?,\n"
                + "    VENCIMENTO=?)\n"
                + "    Where CODIGO-?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setObject(1, fatura.getDataInicioPeriodo());
            pStatement.setObject(2, fatura.getDataFimPeriodo());
            pStatement.setDouble(3, fatura.getJuros());
            pStatement.setInt(4, fatura.getCartao().getCodigo());
            pStatement.setBoolean(5, fatura.isPago());
            pStatement.setDouble(6, fatura.getValor());
            pStatement.setObject(7, fatura.getDataVencimento());
            pStatement.setObject(8, fatura.getCodigo());

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
    public void remover(Fatura fatura) throws PersistenciaException {
        String sql = String.format("DELETE FROM %s WHERE Codigo = ?", nomeTabela);

        Connection connection = null;
        try {
            connection = Conexao.getInstance().getConnection();

            PreparedStatement pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, fatura.getCodigo());

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
    public Fatura listarPorId(Fatura fatura) throws PersistenciaException {
        String sql = String.format("SELECT * FROM %s WHERE Codigo = ?", nomeTabela);
        Connection connection = null;

        try {
            connection = Conexao.getInstance().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(sql);

            pStatement.setLong(1, fatura.getCodigo());

            ResultSet result = pStatement.executeQuery();
            if (result.next()) {
                fatura = montaObjeto(result);
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
        return fatura;
    }

    private Fatura montaObjeto(ResultSet result) throws PersistenciaException, SQLException {
        CartaoCredito cartaoCredito = new CartaoCreditoDAO().listarPorId(new CartaoCredito(result.getInt("CodigoCartao")));

        return new Fatura(result.getInt("Codigo"),
                cartaoCredito,
                result.getDouble("Valor"),
                DateHelper.asLocalDateTime(result.getDate("DataInicio")),
                DateHelper.asLocalDateTime(result.getDate("DataTermino")),
                DateHelper.asLocalDateTime(result.getDate("Vencimento")),
                result.getDouble("Juros"),
                result.getBoolean("Pago"));
    }
}
