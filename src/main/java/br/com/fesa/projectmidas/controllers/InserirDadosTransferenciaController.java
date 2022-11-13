package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.dataaccessobject.AgenciaDAO;
import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.Agencia;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.Transacao;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class InserirDadosTransferenciaController extends BaseController {

    @FXML
    private TextField txtConta;

    @FXML
    private TextField txtCorrentista;

    @FXML
    private TextField txtCPF;

    @FXML
    private ComboBox cmbAgencia;

    @FXML
    private Button btnVoltar;

    public InserirDadosTransferenciaController() throws IOException {
        super(true, false);
    }

    public void initialize() {
        AgenciaDAO dao = new AgenciaDAO();

        try {
            List<Agencia> agencias = dao.listar();

            for (Agencia agencia : agencias) {
                cmbAgencia.getItems().add(agencia);
            }

            txtConta.textProperty().addListener((o) -> {
                atualizarDados();
            });
        } catch (PersistenciaException ex) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(InserirDadosTransferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void confirmar(ActionEvent event) throws IOException {
        try {
            Transacao transacaoAtual = (Transacao) recuperaUserDate(Constantes.chaveTransferenciaSendoRealizada);

            ContaBancariaDAO dao = new ContaBancariaDAO();
            ContaBancaria contaDestino = dao.listarPorNumeroEAgencia(new ContaBancaria(Integer.parseInt(txtConta.getText()), (Agencia) cmbAgencia.getSelectionModel().getSelectedItem()));

            if (contaDestino == null) {
                mostraAlerta(Alert.AlertType.ERROR, "Conta Digitada Não Existe!", "Conta Digitada Não Existe!", "A conta digitada não existe, digite uma conta válida!");
                return;
            } else if (contaDestino.getNumero() == BaseController.getContaBancariaLogada().getNumero()) {
                mostraAlerta(Alert.AlertType.ERROR, "Conta Destino Inválida!", "Conta Destino Inválida!", "Não é possivel realizar uma transferência para sua conta.");
                return;
            }

            transacaoAtual.setDestino(contaDestino);
            adicionaUserDate(Constantes.chaveTransferenciaSendoRealizada, transacaoAtual);
            ProjectMidas.setRoot("revisaoDadosTransacao");
        } catch (Exception erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        }
    }

    @FXML
    private void atualizarDados() {
        try {
            ContaBancariaDAO dao = new ContaBancariaDAO();
            if (cmbAgencia.getSelectionModel().getSelectedItem() != null) {

                ContaBancaria contaComDados = dao.listarPorNumeroEAgencia(new ContaBancaria(Integer.parseInt(txtConta.getText()), (Agencia) cmbAgencia.getSelectionModel().getSelectedItem()));

                if (contaComDados != null) {
                    txtCPF.setText(contaComDados.getCPF());
                    txtCorrentista.setText(contaComDados.getCorrentista());
                } else {
                    txtCPF.setText("");
                    txtCorrentista.setText("");
                }
            } else {
                txtCPF.setText("");
                txtCorrentista.setText("");
            }
        } catch (NumberFormatException erro) {

        } catch (PersistenciaException ex) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(InserirDadosTransferenciaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("selecaoValorTransferencia");
    }
}
