/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

/**
 *
 * @author 081200048
 */
public class pagamentoController extends BaseController {
    
    public pagamentoController() throws IOException {
        super(true, false);
    }
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    private Button btnConfirmar;
    
    @FXML
    private ChoiceBox cmbSelecaoPagamento;
    
    @FXML
    private void voltar(ActionEvent event) throws IOException {
        ProjectMidas.setRoot("mainMenu");
    }
    
    @FXML
    private void confirmar (ActionEvent event) throws IOException{
        //ProjectMidas.setRoot(cmbSelecaoPagamento.get);
    }
    
}
