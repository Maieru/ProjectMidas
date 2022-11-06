package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.exception.ObjetoInvalidoException;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.negocio.ContaBancariaNegocio;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class LoginController extends BaseController {

    @FXML
    private Button btnFazerLogin;

    @FXML
    private Button btnCriarConta;

    @FXML
    private TextField txtConta;

    @FXML
    private TextField txtSenha;

    public LoginController() throws IOException {
        super(false, false);
    }

    public void initialize() {
        txtConta.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, filtroInteiros));
    }

    @FXML
    private void fazerLogin(ActionEvent event) throws IOException, ObjetoInvalidoException, PersistenciaException {
        ContaBancaria conta = new ContaBancaria(Integer.parseInt(txtConta.getText()));
        conta.setSenha(txtSenha.getText());
        ContaBancariaNegocio negocio = new ContaBancariaNegocio(conta);

        if (negocio.validaCredenciais()) {
            BaseController.setContaBancariaLogada(conta);
            ProjectMidas.setRoot("mainMenu");
        } else {
            mostraAlerta(Alert.AlertType.ERROR, "Credenciais erradas!", "As credenciais informadas são inválidas", "As credenciais informadas são inválidas");
        }
    }

    @FXML
    private void criarConta(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("createAccount");
    }
}
