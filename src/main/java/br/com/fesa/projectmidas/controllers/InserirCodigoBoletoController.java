package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.model.Transacao;
import br.com.fesa.projectmidas.negocio.BoletoNegocio;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class InserirCodigoBoletoController extends BaseController {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private TextArea txaCodigoBoleto;

    public InserirCodigoBoletoController() throws IOException {
        super(false, false);
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        try {
            String codigoBoleto = txaCodigoBoleto.getText();
            
            Transacao transacao = BoletoNegocio.leCodigoBoleto(codigoBoleto);
            transacao.setOrigem(BaseController.getContaBancariaLogada());
            
            if (transacao.getDestino() != null && transacao.getOrigem().getNumero() == transacao.getDestino().getNumero()){
                mostraAlerta(Alert.AlertType.ERROR, "Não é possivel pagar um boleto para sua própria conta!", "Não é possivel pagar um boleto para sua própria conta!", "Não é possivel pagar um boleto para sua própria conta!");
                return;
            }
            
            adicionaUserDate(Constantes.chavePagamentoSendoRealizada, transacao);
            adicionaUserDate(Constantes.tipoTransacaoSendoRealizado, transacao.getTipoTransacao());
            
            ProjectMidas.setRoot("revisaoDadosTransacao");
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
