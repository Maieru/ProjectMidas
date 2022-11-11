package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SelecaoMetodoTransferenciaController extends BaseController {

    @FXML
    private ChoiceBox cmbMetodoTransferencia;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Button btnVoltar;

    public SelecaoMetodoTransferenciaController() throws IOException {
        super(true, false);
    }

    public void initialize() {
        cmbMetodoTransferencia.getItems().add(TipoTransacao.TRANSFERENCIA_DOC);
        cmbMetodoTransferencia.getItems().add(TipoTransacao.TRANSFERENCIA_PIX);
        cmbMetodoTransferencia.getItems().add(TipoTransacao.TRANSFERENCIA_TED);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        apagaUserDate(Constantes.chaveTransferenciaSendoRealizada);
        ProjectMidas.setRoot("mainMenu");
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        try {

            TipoTransacao tipoSelecionado = (TipoTransacao)cmbMetodoTransferencia.getSelectionModel().getSelectedItem();

            if (tipoSelecionado == null) {
                mostraAlerta(Alert.AlertType.ERROR, "Selecione um tipo de transferência.", "Selecione um tipo de transferência.", "Selecione um tipo de transferência.");
                return;
            }

            Transacao transacaoAtual = new Transacao();
            transacaoAtual.setTipoTransacao(tipoSelecionado);
            transacaoAtual.setOrigem(BaseController.getContaBancariaLogada());
            
            adicionaUserDate(Constantes.chaveTransferenciaSendoRealizada, transacaoAtual);
            ProjectMidas.setRoot("selecaoValorTransferencia");
        } catch (Exception erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        }
    }
}
