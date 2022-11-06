package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import static br.com.fesa.projectmidas.controllers.BaseController.filtroInteiros;
import br.com.fesa.projectmidas.exception.ObjetoInvalidoException;
import br.com.fesa.projectmidas.model.Agencia;
import br.com.fesa.projectmidas.negocio.AgenciaNegocio;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class EditAgenciaController extends BaseController {

    private Agencia agenciaSendoEditada = null;

    @FXML
    private TextField txtAgencia;

    @FXML
    private TextField txtLocalizacao;

    public EditAgenciaController() throws IOException {
        super(true, true);
    }

    public void initialize() {
        txtAgencia.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), null, filtroInteiros));
        Object agenciaEditando = recuperaUserDate();

        if (agenciaEditando != null) {
            agenciaSendoEditada = (Agencia)agenciaEditando;
            
            txtAgencia.setText(Integer.toString(agenciaSendoEditada.getCodigo()));
            txtLocalizacao.setText(agenciaSendoEditada.getLocalizacao());
        }
    }

    @FXML
    private void criarAgencia(ActionEvent event) throws IOException {
        try {
            Agencia agenciaASerCriada = new Agencia(Integer.parseInt(txtAgencia.getText()), txtLocalizacao.getText());

            AgenciaNegocio agenciaNegocio = new AgenciaNegocio(agenciaASerCriada);
            agenciaNegocio.validaESalvaAgencia(agenciaSendoEditada != null);
            mostraAlerta(Alert.AlertType.INFORMATION, "Agencia Criada com Sucesso!", "Agencia Criada com Sucesso!", "Sua agencia foi criada com sucesso!", (t) -> {
                try {
                    ProjectMidas.setRoot("gerenciarAgencias");
                } catch (IOException ex) {
                    Logger.getLogger(CreateAccountController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (ObjetoInvalidoException erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro!", "Erro!", erro.getMessage());
        } catch (Exception erro) {
            mostraAlerta(Alert.AlertType.ERROR, "Erro não esperado!", "Erro não esperado!", "Um erro não esperado ocorreu.");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, erro);
        }
    }
}
