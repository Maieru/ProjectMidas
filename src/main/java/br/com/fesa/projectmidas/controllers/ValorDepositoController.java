package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 *
 * @author 081200048
 */
public class ValorDepositoController extends BaseController{
    
    public ValorDepositoController() throws IOException {
        super(true, false);
    }
    
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private TextField txtValorDeposito;
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("gerenciarAgencias");
    }
    
    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("boletoDeposito");
    }
}
