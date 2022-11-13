package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.dataaccessobject.AgenciaDAO;
import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.dataaccessobject.TipoInvestimentoDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.Agencia;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.Investimento;
import br.com.fesa.projectmidas.model.TipoInvestimento;
import br.com.fesa.projectmidas.negocio.InvestimentoNegocio;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class NovoInvestimentoController extends BaseController {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnConfirmar;

    @FXML
    private ComboBox cmbTipoInvestimento;

    @FXML
    private TextField txtValor;

    @FXML
    private Label lblPorcentagemRendimento;

    @FXML
    private Label lblValorMinimo;

    @FXML
    private Label lblRendimentoDiario;

    public NovoInvestimentoController() throws IOException {
        super(true, false);
    }

    public void initialize() {
        TipoInvestimentoDAO dao = new TipoInvestimentoDAO();

        lblPorcentagemRendimento.setText("");
        lblValorMinimo.setText("");
        lblRendimentoDiario.setText("");

        try {
            List<TipoInvestimento> investimentos = dao.listar();

            for (TipoInvestimento tipoInvestimento : investimentos) {
                cmbTipoInvestimento.getItems().add(tipoInvestimento);
            }

            txtValor.textProperty().addListener((o) -> {
                atualizarDados();
            });
        } catch (PersistenciaException ex) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(InserirDadosTransferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void atualizarDados() {
        try {
            TipoInvestimento tipoSelecionado = (TipoInvestimento) cmbTipoInvestimento.getSelectionModel().getSelectedItem();
            if (tipoSelecionado != null) {

                lblPorcentagemRendimento.setText(String.format("%.5f", tipoSelecionado.getRendimentos()) + " %");
                lblValorMinimo.setText(String.format("R$ %.2f", tipoSelecionado.getValorMinimo()));

                if (txtValor.getText() != null) {
                    double valorDigitado = Double.parseDouble(txtValor.getText());
                    lblRendimentoDiario.setText(String.format("R$ %.2f", valorDigitado * tipoSelecionado.getRendimentos()));
                }
            } else {
                lblPorcentagemRendimento.setText("");
                lblValorMinimo.setText("");
                lblRendimentoDiario.setText("");
            }
        } catch (NumberFormatException erro) {

        }
    }

    @FXML
    private void confirmar() {
        try {
            Investimento investimento = new Investimento();
            ContaBancaria conta = BaseController.getContaBancariaLogada();

            TipoInvestimento tipoSelecionado = (TipoInvestimento) cmbTipoInvestimento.getSelectionModel().getSelectedItem();
            double valor = Double.parseDouble(txtValor.getText());

            if (tipoSelecionado == null) {
                mostraAlerta(Alert.AlertType.ERROR, "Selecione um tipo de investimento!", "Selecione um tipo de investimento!", "Selecione um tipo de investimento!");
                return;
            }

            if (valor > conta.getSaldo()) {
                mostraAlerta(Alert.AlertType.ERROR, "Saldo na conta insuficiente!", "Saldo na conta insuficiente!", "Saldo na conta insuficiente!");
                return;
            }

            investimento.setTipo(tipoSelecionado);
            investimento.setValorInvestido(valor);
            investimento.setCarteira(conta.getCarteira());

            InvestimentoNegocio.fazInvestimento(BaseController.getContaBancariaLogada(), investimento);
            voltar(null);
        } catch (NumberFormatException erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Digite um tipo de investimento válido!", "Digite um tipo de investimento válido!", "Digite um tipo de investimento válido!");
        } catch (Exception e) {

        }
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("TelaInicialInvestimento");
    }
}
