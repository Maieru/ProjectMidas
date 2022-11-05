package br.com.fesa.projectmidas.controllers;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

public class CreateAccountController extends BaseController implements Serializable{

    @FXML
    private TextField txtAgencia;

    @FXML
    private TextField txtNomeCorrentista;

    public CreateAccountController() throws IOException {
        super(false, false);
    }

    public void initialize() {
        txtAgencia.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, filtroInteiros));
    }
}
