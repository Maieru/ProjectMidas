package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.negocio.BoletoNegocio;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class BoletoDepositoController extends BaseController {

    @FXML
    private TextArea txaCodBoleto;

    @FXML
    private Button btnCopiar;
    @FXML
    private Button btnVoltar;

    public BoletoDepositoController() throws IOException {
        super(true, false);
    }

    public void initialize() {
        double valorDoBoleto = (double) recuperaUserDate(Constantes.chaveValorDeposito);
        String codigoBoleto = BoletoNegocio.geraCodigoBoleto(BaseController.getContaBancariaLogada(), valorDoBoleto);

        txaCodBoleto.setText(codigoBoleto);
    }

    @FXML
    private void copiarCodigo(ActionEvent event) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        ClipboardContent conteudo = new ClipboardContent();
        conteudo.putString(txaCodBoleto.getText());
        clipboard.setContent(conteudo);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }

}
