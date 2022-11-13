package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.dataaccessobject.InvestimentoDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.Agencia;
import br.com.fesa.projectmidas.model.CarteiraInvestimento;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.Investimento;
import br.com.fesa.projectmidas.model.TipoTransacao;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaInicialInvestimentoController extends BaseController {

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnNovoInvestimento;

    @FXML
    private TableView tbvInvestimentos;

    @FXML
    private TableColumn clnNome;

    @FXML
    private TableColumn clnValor;
    
    @FXML
    private TableColumn clnData;
    
    @FXML
    private TableColumn clnRentabilidade;
    
    @FXML
    private Label lblSaldo;
    
    @FXML
    private Label lblRendimento;
    
    @FXML
    private Label lblRendimentoPorcentagem;

    public TelaInicialInvestimentoController() throws IOException {
        super(true, false);
    }

    public void initialize() throws PersistenciaException, PersistenciaException {
        ContaBancaria contaLogada = BaseController.getContaBancariaLogada();
        CarteiraInvestimento carteira = contaLogada.getCarteira();
        
        if (carteira != null ){
            lblSaldo.setText(String.format("R$ %.2f", carteira.getSaldo()));
            lblRendimento.setText(String.format("R$ %.2f", carteira.getSaldo() * carteira.getRendimento()));
            lblRendimentoPorcentagem.setText(String.format("%.5f", carteira.getRendimento()) + " %");
        }
        
        clnData.setCellValueFactory(new PropertyValueFactory<Investimento, Integer>("data"));
        clnNome.setCellValueFactory(new PropertyValueFactory<Investimento, String>("nomeFormatado"));
        clnRentabilidade.setCellValueFactory(new PropertyValueFactory<Investimento, String>("rendimento"));
        clnValor.setCellValueFactory(new PropertyValueFactory<Investimento, String>("valorInvestido"));

        setItensTableView(tbvInvestimentos, new InvestimentoDAO().listarPorCarteira(carteira)); 
    }

    @FXML
    private void novoInvestimento(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("novoInvestimento");
    }
    
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }
}
