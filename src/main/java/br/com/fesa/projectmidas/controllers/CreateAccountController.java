package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.dataaccessobject.AgenciaDAO;
import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.exception.ObjetoInexistenteException;
import br.com.fesa.projectmidas.exception.ObjetoInvalidoException;
import br.com.fesa.projectmidas.model.Agencia;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.negocio.ContaBancariaNegocio;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class CreateAccountController extends BaseController {

    @FXML
    private TextField txtAgencia;

    @FXML
    private TextField txtNomeCorrentista;

    @FXML
    private TextField txtCPF;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnCriarConta;

    public CreateAccountController() throws IOException {
        super(false, false);
    }

    public void initialize() {
        txtAgencia.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, filtroInteiros));
    }

    @FXML
    private void criarConta(ActionEvent event) throws IOException {
        try {
            Agencia agenciaDaConta = new AgenciaDAO().listarPorId(new Agencia(Integer.parseInt(txtAgencia.getText())));
            Integer numeroConta = new ContaBancariaDAO().getNextNumeroConta();

            if (agenciaDaConta == null) {
                throw new ObjetoInexistenteException("A agencia informada não existe");
            }

            ContaBancaria contaASerCriada = new ContaBancaria(numeroConta, agenciaDaConta, txtSenha.getText(), txtNomeCorrentista.getText(), txtCPF.getText());
            ContaBancariaNegocio contaNegocio = new ContaBancariaNegocio(contaASerCriada);
            contaNegocio.validaESalvaContaBancaria();
            mostraAlerta(AlertType.INFORMATION, "Conta Criada com Sucesso!", "Conta Criada com Sucesso!", String.format("Sua conta foi criada com sucesso. O número de sua conta é: %d", contaASerCriada.getNumero()), (t) -> {
                try {
                    ProjectMidas.setRoot("login");
                } catch (IOException ex) {
                    Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (ObjetoInvalidoException erro) {
            mostraAlerta(AlertType.ERROR, "Erro!", "Erro!", erro.getMessage());
        } catch (ObjetoInexistenteException erro) {
            mostraAlerta(AlertType.ERROR, "Erro!", "Erro!", erro.getMessage());
        } catch (Exception erro) {
            mostraAlerta(AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        }
    }

    @FXML
    private void retornar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("login");
    }
}
