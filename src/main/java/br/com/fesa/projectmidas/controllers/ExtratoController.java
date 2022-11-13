package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.dataaccessobject.TransacaoDAO;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import br.com.fesa.projectmidas.model.ContaBancaria;
import br.com.fesa.projectmidas.model.Meses;
import br.com.fesa.projectmidas.model.Transacao;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExtratoController extends BaseController {

    @FXML
    private TableView tbvTransacoes;

    @FXML
    private TableColumn clnTipo;

    @FXML
    private TableColumn clnData;

    @FXML
    private TableColumn clnDescricao;

    @FXML
    private TableColumn clnValor;

    @FXML
    private Button btnVoltar;

    @FXML
    private BarChart bchGastosUltimosMeses;

    public ExtratoController() throws IOException {
        super(false, false);
    }

    public void initialize() throws PersistenciaException, PersistenciaException {
        ContaBancaria contaLogada = BaseController.getContaBancariaLogada();

        clnTipo.setCellValueFactory(new PropertyValueFactory<Transacao, String>("tipoTransacao"));
        clnData.setCellValueFactory(new PropertyValueFactory<Transacao, String>("dataTransacao"));
        clnDescricao.setCellValueFactory(new PropertyValueFactory<Transacao, String>("descricao"));
        clnValor.setCellValueFactory(new PropertyValueFactory<Transacao, String>("valor"));

        TransacaoDAO transacaoDao = new TransacaoDAO();
        List<Transacao> transacoesDaConta = transacaoDao.listarPorConta(contaLogada);
        setItensTableView(tbvTransacoes, transacoesDaConta);

        // Criação do Gráfico
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        Map<Meses, Double> entradasPorMes = new HashMap<Meses, Double>();
        Map<Meses, Double> saidasPorMes = new HashMap<Meses, Double>();

        for (Transacao transacao : transacoesDaConta) {
            if (transacao.getDataTransacao().isAfter(transacao.getDataTransacao().minus(3, ChronoUnit.MONTHS))) {
                switch (transacao.getTipoTransacao()) {
                    case INVESTIMENTO:
                    case PAGAMENTO_BOLETO:
                    case PAGAMENTO_PIX:
                    case TRANSFERENCIA_DOC:
                    case TRANSFERENCIA_PIX:
                    case TRANSFERENCIA_TED:
                    case SAQUE:
                        if (!saidasPorMes.containsKey(Meses.getById(transacao.getDataTransacao().getMonthValue()))) {
                            saidasPorMes.put(Meses.getById(transacao.getDataTransacao().getMonthValue()), transacao.getValor());
                        } else {
                            saidasPorMes.replace(Meses.getById(transacao.getDataTransacao().getMonthValue()), transacao.getValor() + saidasPorMes.get(Meses.getById(transacao.getDataTransacao().getMonthValue())));
                        }
                        break;
                    case RENDIMENTO_INVESTIMENTO:
                    case DEPOSITO:
                        if (!entradasPorMes.containsKey(Meses.getById(transacao.getDataTransacao().getMonthValue()))) {
                            entradasPorMes.put(Meses.getById(transacao.getDataTransacao().getMonthValue()), transacao.getValor());
                        } else {
                            entradasPorMes.replace(Meses.getById(transacao.getDataTransacao().getMonthValue()), transacao.getValor() + entradasPorMes.get(Meses.getById(transacao.getDataTransacao().getMonthValue())));
                        }
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        }

        XYChart.Series mes1 = new XYChart.Series();
        XYChart.Series mes2 = new XYChart.Series();
        XYChart.Series mes3 = new XYChart.Series();

        Meses mesAtual = Meses.getById(LocalDateTime.now().getMonthValue());

        mes1.setName(mesAtual.getDescricao());
        mes2.setName(Meses.getById(LocalDateTime.now().getMonthValue() - 1).getDescricao());
        mes3.setName(Meses.getById(LocalDateTime.now().getMonthValue() - 2).getDescricao());

        mes1.getData().add(new XYChart.Data("Entrada", entradasPorMes.get(mesAtual) == null ? 0 : entradasPorMes.get(mesAtual)));
        mes1.getData().add(new XYChart.Data("Saída", saidasPorMes.get(mesAtual) == null ? 0 : saidasPorMes.get(mesAtual)));

        mes2.getData().add(new XYChart.Data("Entrada", entradasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 1).getDescricao()) == null ? 0 : entradasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 1))));
        mes2.getData().add(new XYChart.Data("Saída", saidasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 1)) == null ? 0 : saidasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 1))));
        
        mes3.getData().add(new XYChart.Data("Entrada", entradasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 2)) == null ? 0 : entradasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 2))));
        mes3.getData().add(new XYChart.Data("Saída", saidasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 2)) == null ? 0 : saidasPorMes.get(Meses.getById(LocalDateTime.now().getMonthValue() - 2))));

        bchGastosUltimosMeses.getData().addAll(mes3, mes2, mes1);
    }

    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }
}
