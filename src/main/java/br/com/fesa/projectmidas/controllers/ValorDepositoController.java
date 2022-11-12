package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 *
 * @author 081200048
 */
public class ValorDepositoController extends BaseController {

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
        ProjectMidas.setRoot("mainMenu");
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        try {
            double valorDepositado = Double.parseDouble(txtValorDeposito.getText());
            adicionaUserDate(Constantes.chaveValorDeposito, valorDepositado);
            ProjectMidas.setRoot("boletoDeposito");
        } catch (NumberFormatException ex) {
            mostraAlerta(Alert.AlertType.ERROR, "O valor digitado está inválido!", "O valor digitado está inválido!", "O valor digitado está inválido!");
        }
    }
}
