package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController extends BaseController {

    @FXML
    private Button btnGerenciarAgencias;

    public MainMenuController() throws IOException {
        super(true, false);
    }
    @FXML
    private Label Midas ;
    @FXML
    private Label lblNome ;
    @FXML
    private Label lblConta;
    @FXML
    private Label lblSaldo;
    
    @FXML
    private Button btnSair ;
    
    @FXML
    private Button btnConfigs ;

    public void initialize() {
        btnGerenciarAgencias.visibleProperty().set(this.isAdmin());
        
        lblNome.setText(BaseController.getContaBancariaLogada().getCorrentista());
        lblConta.setText("Banco: " + BaseController.getContaBancariaLogada().getNumero().toString() + " | Agencia: " + Integer.toString(BaseController.getContaBancariaLogada().getAgencia().getCodigo()));
        lblSaldo.setText("Saldo: " + Double.toString(BaseController.getContaBancariaLogada().getSaldo()));
    }

    @FXML
    private void gerenciarAgencias(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("gerenciarAgencias");
    }
    
    @FXML
    private void sair(ActionEvent event) throws IOException {
    
        BaseController.setContaBancariaLogada(null);
        ProjectMidas.setRoot("login");
    }
}
