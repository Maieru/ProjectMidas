package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.model.TipoTransacao;
import java.io.IOException;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SelecaoMetodoTransferencia extends BaseController {

    @FXML
    private ChoiceBox cmbMetodoTransferencia;

    @FXML
    private Button btnConfirmar;
    
    public SelecaoMetodoTransferencia() throws IOException {
        super(true, false);
    }
    
    public void initialize() {
        cmbMetodoTransferencia.getItems().add(TipoTransacao.TRANSFERENCIA_DOC);
        cmbMetodoTransferencia.getItems().add(TipoTransacao.TRANSFERENCIA_PIX);
        cmbMetodoTransferencia.getItems().add(TipoTransacao.TRANSFERENCIA_TED);
    }
    
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }
    
    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        cmbMetodoTransferencia.getSelectionModel().getSelectedItem();
    }
}
