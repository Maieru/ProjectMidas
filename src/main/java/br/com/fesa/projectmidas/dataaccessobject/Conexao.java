package br.com.fesa.projectmidas.dataaccessobject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;


public class Conexao {
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle("derb", new Locale("pt", "BR"));
    private static Conexao conexao;

    private Conexao() {
    }

    public static Conexao getInstance() {
        if (conexao == null) {
            conexao = new Conexao();
        }
        return conexao;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection connection = DriverManager.getConnection(BUNDLE.getString("url"), BUNDLE.getString("usuario"), BUNDLE.getString("senha"));
        return connection;
    }
}
