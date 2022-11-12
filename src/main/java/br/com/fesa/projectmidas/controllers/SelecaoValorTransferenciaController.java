package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import static br.com.fesa.projectmidas.controllers.BaseController.filtroInteiros;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class SelecaoValorTransferenciaController extends BaseController {

    @FXML
    private TextField txtValor;

    @FXML
    private Button btnConfirmar;

        @FXML
        private Button btnVoltar;

    public SelecaoValorTransferenciaController() throws IOException {
        super(true, false);
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        try {

            double valorTransacao = Double.parseDouble(txtValor.getText());
            
            Transacao transacaoAtual = (Transacao)recuperaUserDate(Constantes.chaveTransferenciaSendoRealizada);
            transacaoAtual.setValor(valorTransacao);

            adicionaUserDate(Constantes.chaveTransferenciaSendoRealizada, transacaoAtual);
            ProjectMidas.setRoot("inserirDadosTransferencia");
        }
        catch (NumberFormatException erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Digite um valor válido.", "Digite um valor válido.", "Digite um valor válido.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        }
        catch (Exception erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        }
    }
    
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("selecaoMetodoTransferencia");
    }
}
