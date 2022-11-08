package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController extends BaseController {
    
    @FXML
    private Button btnGerenciarAgencias;
    
    public MainMenuController() throws IOException {
        super(true, false);
    }
    
    public void initialize() {
        btnGerenciarAgencias.visibleProperty().set(this.isAdmin());
    }
    
    @FXML
    private void gerenciarAgencias(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("gerenciarAgencias");
    }
}
