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
import javafx.scene.control.Label;

public class RevisaoDadosTransferenciaController extends BaseController {

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
    
    public RevisaoDadosTransferenciaController() throws IOException {
        super(true, false);
    }
    
    public void initialize() {
        Transacao transferenciaSendoRealizada = (Transacao)recuperaUserDate(Constantes.chaveTransferenciaSendoRealizada);
        lblMetodo.setText(transferenciaSendoRealizada.getTipoTransacao().getDescricao());
        lblValor.setText(Double.toString(transferenciaSendoRealizada.getValor()));
        lblAgencia.setText(transferenciaSendoRealizada.getDestino().getAgencia().toString());
        lblConta.setText(Integer.toString(transferenciaSendoRealizada.getDestino().getNumero()));
        lblCorrentista.setText(transferenciaSendoRealizada.getDestino().getCorrentista());
        lblCPF.setText(transferenciaSendoRealizada.getDestino().getCPF());
    }
    
    @FXML
    private void confirmar(ActionEvent event) throws IOException {
    }
    
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("inserirDadosTransferencia");
    }
}
