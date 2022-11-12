package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class SelecaoMetodoPagamento extends BaseController {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private ChoiceBox cmbSelecaoPagamento;

    public SelecaoMetodoPagamento() throws IOException {
        super(false, false);
    }

    public void initialize() {
        cmbSelecaoPagamento.getItems().add(TipoTransacao.PAGAMENTO_BOLETO);
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        try {
            TipoTransacao tipoSelecionado = (TipoTransacao) cmbSelecaoPagamento.getSelectionModel().getSelectedItem();

            if (tipoSelecionado == null) {
                mostraAlerta(Alert.AlertType.ERROR, "Selecione um tipo de pagamento.", "Selecione um tipo de pagamento.", "Selecione um tipo de pagamento.");
                return;
            }

            if (tipoSelecionado == TipoTransacao.PAGAMENTO_BOLETO) {
                ProjectMidas.setRoot("inserirCodigoBoleto");
            }
        } catch (NumberFormatException erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Digite um valor válido.", "Digite um valor válido.", "Digite um valor válido.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        } catch (Exception erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        }
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }
}
