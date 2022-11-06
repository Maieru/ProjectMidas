package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.model.ContaBancaria;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextFormatter.Change;
import javafx.stage.Stage;

public abstract class BaseController {
    private static Stage stage;
    private static ContaBancaria contaBancariaLogada;
    private boolean autenticacaoNecessaria;
    private boolean permissaoAdministradorNecessaria;

    public BaseController(boolean autenticacaoNecessaria, boolean permissaoAdministradorNecessaria) throws IOException {
        this.autenticacaoNecessaria = autenticacaoNecessaria;
        this.permissaoAdministradorNecessaria = permissaoAdministradorNecessaria;

        BaseController controller = this;
        // Utilizado para redirecionar automaticamente o usuário para a tela de forbidden
        // caso não possua a credencial necessária.
        // O delay é necessário por que o construtor é executado antes da tela ser carregada.
        new Timer().schedule(new TimerTask() {
            BaseController controllerVerificada = controller;

            @Override
            public void run() {
                try {
                    if (!controllerVerificada.isAutenticado() || (controllerVerificada.permissaoAdministradorNecessaria && !controllerVerificada.isAdmin())) {
                        ProjectMidas.setRoot("forbidden");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.cancel();
            }
        }, 0, 50);
    }

    private boolean isAutenticado() {
        if (this.isAutenticacaoNecessaria() && BaseController.getContaBancariaLogada() == null) {
            return false;
        }

        return true;
    }

    public boolean isAdmin() {
        return true;
    }

    public static ContaBancaria getContaBancariaLogada() {
        return contaBancariaLogada;
    }

    public static void setContaBancariaLogada(ContaBancaria contaBancariaLogada) {
        BaseController.contaBancariaLogada = contaBancariaLogada;
    }

    public boolean isAutenticacaoNecessaria() {
        return this.autenticacaoNecessaria;
    }

    public void setAutenticacaoNecessaria(boolean autenticacaoNecessaria) {
        this.autenticacaoNecessaria = autenticacaoNecessaria;
    }

    public boolean isPermissaoAdministradorNecessaria() {
        return this.permissaoAdministradorNecessaria;
    }

    public void setPermissaoAdministradorNecessaria(boolean permissaoAdministradorNecessaria) {
        this.permissaoAdministradorNecessaria = permissaoAdministradorNecessaria;
    }

    protected void mostraAlerta(AlertType tipoAlerta, String titulo, String cabecalho, String texto) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    protected void mostraAlerta(AlertType tipoAlerta, String titulo, String cabecalho, String texto, Consumer<? super ButtonType> callback) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(texto);
        alert.showAndWait().ifPresent(callback);
    }

    protected void adicionaUserDate(Object objeto, ActionEvent event){
        Node node = (Node)event.getSource();
        stage = (Stage)node.getScene().getWindow();
        stage.setUserData(objeto);
    }
    
    protected Object recuperaUserDate(){
        if (stage == null)
            return null;
                    
        return stage.getUserData();
    }
    
    protected static UnaryOperator<Change> filtroInteiros = change -> {
        String novoTexto = change.getControlNewText();
        if (novoTexto.matches("-?([0-9][0-9]{0,3})?")) {
            return change;
        }
        return null;
    };

    protected static UnaryOperator<Change> filtroCPF = change -> {
        String novoTexto = change.getControlNewText();
        if (novoTexto.matches("([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})\n")) {
            return change;
        }
        return null;
    };
}
