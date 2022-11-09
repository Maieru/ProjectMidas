package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.dataaccessobject.AgenciaDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.Agencia;
import br.com.fesa.projectmidas.negocio.Constantes;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class GerenciarAgenciasController extends BaseController {

    @FXML
    private Button btnCriar;

    @FXML
    private Button btnEditar;
    
    @FXML
    private Button btnVoltar;

    @FXML
    private TableView tbvAgencias;

    @FXML
    private TableColumn clnCodigo;

    @FXML
    private TableColumn clnLocalizacao;

    public GerenciarAgenciasController() throws IOException {
        super(true, true);
    }

    public void initialize() throws PersistenciaException {
        clnCodigo.setCellValueFactory(new PropertyValueFactory<Agencia, Integer>("codigo"));
        clnLocalizacao.setCellValueFactory(new PropertyValueFactory<Agencia, String>("localizacao"));

        setItensTableView();
    }

    @FXML
    private void criar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("editAgencia");
    }
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }

    @FXML
    private void editar(ActionEvent event) throws IOException {
        if (tbvAgencias.getSelectionModel().getSelectedItem() != null) {
            adicionaUserDate(Constantes.chaveUserDateAgencia, tbvAgencias.getSelectionModel().getSelectedItem());
            ProjectMidas.setRoot("editAgencia");
        } else {
            mostraAlerta(Alert.AlertType.INFORMATION, "Selecione uma agencia!", "Selecione uma agencia!", "Para realizar a edição, selecione uma agência na tabela!");
        }
    }

    @FXML
    private void deletar(ActionEvent event) throws IOException, PersistenciaException {
        if (tbvAgencias.getSelectionModel().getSelectedItem() != null) {
            AgenciaDAO dao = new AgenciaDAO();
            dao.remover((Agencia) tbvAgencias.getSelectionModel().getSelectedItem());

            setItensTableView();
        } else {
            mostraAlerta(Alert.AlertType.INFORMATION, "Selecione uma agencia!", "Selecione uma agencia!", "Para realizar a exclusão, selecione uma agência na tabela!");
        }
    }

    private void setItensTableView() throws PersistenciaException {
        List<Agencia> agencias = new AgenciaDAO().listar();
        var teste = FXCollections.observableList(agencias);

        tbvAgencias.setItems(teste);
    }
}
