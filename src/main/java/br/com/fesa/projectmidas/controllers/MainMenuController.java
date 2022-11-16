package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.negocio.RendimentoNegocio;
import java.io.IOException;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainMenuController extends BaseController {

    @FXML
    private Button btnGerenciarAgencias;

    @FXML
    private Button btnInvestimentos;

    @FXML
    private Button btnPagamentos;

    @FXML
    private Button btnDepositos;

    @FXML
    private Button btnExtrato;

    @FXML
    private Button btnTransferencia;

    @FXML
    private Button btnAvancarPeriodo;

    @FXML
    private Button btnAdicionarSaldo;

    @FXML
    private GridPane gpnPainelAdministrador;

    @FXML
    private Label Midas;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblConta;

    @FXML
    private Label lblSaldo;

    public MainMenuController() throws IOException {
        super(true, false);
    }
    @FXML
    private Button btnSair;

    @FXML
    private Button btnConfigs;

    public void initialize() {
        gpnPainelAdministrador.visibleProperty().set(this.isAdmin());

        lblNome.setText(BaseController.getContaBancariaLogada().getCorrentista());
        lblConta.setText("Banco: " + BaseController.getContaBancariaLogada().getNumero().toString() + " | Agencia: " + Integer.toString(BaseController.getContaBancariaLogada().getAgencia().getCodigo()));
        lblSaldo.setText("Saldo: " + String.format("R$ %.2f", BaseController.getContaBancariaLogada().getSaldo()));
    }

    @FXML
    private void gerenciarAgencias(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("gerenciarAgencias");
    }

    @FXML
    private void sair(ActionEvent event) throws IOException {

        BaseController.setContaBancariaLogada(null);
        ProjectMidas.setRoot("login");
    }

    @FXML
    private void handlerInvestimentos(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("telaInicialInvestimento");
    }

    @FXML
    private void handlerPagamentos(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("selecaoMetodoPagamento");
    }

    @FXML
    private void handlerDeposito(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("valorDeposito");
    }

    @FXML
    private void handlerTransferencia(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("selecaoMetodoTransferencia");
    }

    @FXML
    private void extrato(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("extrato");
    }

    @FXML
    private void avancarPeriodo(ActionEvent event) throws IOException, PersistenciaException {
        ContaBancariaDAO contaDao = new ContaBancariaDAO();

        for (ContaBancaria conta : contaDao.listar()) {
            RendimentoNegocio.calculaRendimentos(conta);
            contaDao.alterar(conta);
        }

        BaseController.setContaBancariaLogada(contaDao.listarPorNumero(BaseController.getContaBancariaLogada()));

        initialize();
    }

    @FXML
    private void adicionarSaldo(ActionEvent event) throws IOException, PersistenciaException {
        ContaBancariaDAO contaDao = new ContaBancariaDAO();

        BaseController.getContaBancariaLogada().modificaSaldo(1000);
        contaDao.alterar(BaseController.getContaBancariaLogada());
        
        initialize();
    }
}
