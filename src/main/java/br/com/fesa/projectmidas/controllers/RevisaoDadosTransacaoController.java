package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.exception.NegocioException;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.TipoTransacao;
import br.com.fesa.projectmidas.model.Transacao;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class RevisaoDadosTransacaoController extends BaseController {

    @FXML
    private Label lblMetodo;

    @FXML
    private Label lblValor;

    @FXML
    private Label lblAgencia;

    @FXML
    private Label lblConta;

    @FXML
    private Label lblCorrentista;

    @FXML
    private Label lblCPF;

    public RevisaoDadosTransacaoController() throws IOException {
        super(true, false);
    }

    public void initialize() {
        TipoTransacao tipoTransacao = (TipoTransacao) recuperaUserDate(Constantes.tipoTransacaoSendoRealizado);

        Transacao transacaoSendoRealizada = retornaTransacaoSendoPreenchida(tipoTransacao);

        lblMetodo.setText(transacaoSendoRealizada.getTipoTransacao().getDescricao());
        lblValor.setText(Double.toString(transacaoSendoRealizada.getValor()));

        if (transacaoSendoRealizada.getDestino() != null) {
            lblAgencia.setText(transacaoSendoRealizada.getDestino().getAgencia().toString());
            lblConta.setText(Integer.toString(transacaoSendoRealizada.getDestino().getNumero()));
            lblCorrentista.setText(transacaoSendoRealizada.getDestino().getCorrentista());
            lblCPF.setText(transacaoSendoRealizada.getDestino().getCPF());
        } else {
            lblAgencia.setText("Externo");
            lblConta.setText("Externo");
            lblCorrentista.setText("Externo");
            lblCPF.setText("Externo");
        }
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        try {
            TipoTransacao tipoTransacao = (TipoTransacao) recuperaUserDate(Constantes.tipoTransacaoSendoRealizado);
            Transacao transferenciaSendoRealizada = retornaTransacaoSendoPreenchida(tipoTransacao);
            transferenciaSendoRealizada.transaciona();
            apagaUserDate(Constantes.chaveTransferenciaSendoRealizada);
            ProjectMidas.setRoot("sucesso");
        } catch (NegocioException ex) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro no Preenchimento dos Dados", "Os dados informados não ", ex.getMessage());
        } catch (PersistenciaException ex) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("inserirDadosTransferencia");
    }

    private Transacao retornaTransacaoSendoPreenchida(TipoTransacao tipoTransacao) {
        if (tipoTransacao == TipoTransacao.PAGAMENTO_BOLETO || tipoTransacao == TipoTransacao.PAGAMENTO_PIX) {
            return (Transacao) recuperaUserDate(Constantes.chavePagamentoSendoRealizada);
        } else if (tipoTransacao == TipoTransacao.TRANSFERENCIA_DOC || tipoTransacao == TipoTransacao.TRANSFERENCIA_PIX || tipoTransacao == TipoTransacao.TRANSFERENCIA_TED) {
            return (Transacao) recuperaUserDate(Constantes.chaveTransferenciaSendoRealizada);
        } else {
            return null;
        }
    }
}
