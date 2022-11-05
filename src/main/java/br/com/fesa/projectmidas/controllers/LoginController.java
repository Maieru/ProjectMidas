package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.model.ContaBancaria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
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
    
    @FXML
    private void fazerLogin(ActionEvent event) throws IOException{
        ProjectMidas.setRoot("mainMenu");
    }
    
    @FXML
    private void criarConta(ActionEvent event) throws IOException{
        ProjectMidas.setRoot("createAccount");
    }
}