package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SucessoController extends BaseController {

    public SucessoController() throws IOException {
        super(false, false);
    }

    @FXML
    private Button btnVoltar;

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }
}
