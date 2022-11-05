package br.com.fesa.projectmidas.controllers;

import br.com.fesa.projectmidas.ProjectMidas;
import br.com.fesa.projectmidas.model.ContaBancaria;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextFormatter.Change;

public abstract class BaseController{

    private static ContaBancaria contaBancariaLogada;
    private static boolean autenticacaoNecessaria;
    private static boolean permissaoAdministradorNecessaria;

    public BaseController(boolean autenticacaoNecessaria, boolean permissaoAdministradorNecessaria) throws IOException {
        this.autenticacaoNecessaria = autenticacaoNecessaria;
        this.permissaoAdministradorNecessaria = permissaoAdministradorNecessaria;

        // Utilizado para redirecionar automaticamente o usuário para a tela de forbidden
        // caso não possua a credencial necessária.
        // O delay é necessário por que o construtor é executado antes da tela ser carregada.
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (!BaseController.isAutenticado()) {
                        ProjectMidas.setRoot("forbidden");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.cancel();
            }
        }, 0, 50);
    }

    private static boolean isAutenticado() {
        if (BaseController.isAutenticacaoNecessaria() && BaseController.getContaBancariaLogada() == null) {
            return false;
        }

        return true;
    }
    
    public static ContaBancaria getContaBancariaLogada() {
        return contaBancariaLogada;
    }

    public static void setContaBancariaLogada(ContaBancaria contaBancariaLogada) {
        BaseController.contaBancariaLogada = contaBancariaLogada;
    }

    public static boolean isAutenticacaoNecessaria() {
        return autenticacaoNecessaria;
    }

    public static void setAutenticacaoNecessaria(boolean autenticacaoNecessaria) {
        BaseController.autenticacaoNecessaria = autenticacaoNecessaria;
    }

    public static boolean isPermissaoAdministradorNecessaria() {
        return permissaoAdministradorNecessaria;
    }

    public static void setPermissaoAdministradorNecessaria(boolean permissaoAdministradorNecessaria) {
        BaseController.permissaoAdministradorNecessaria = permissaoAdministradorNecessaria;
    }

    protected UnaryOperator<Change> filtroInteiros = change -> {
        String newText = change.getControlNewText();
        if (newText.matches("-?([0-9][0-9]{0,3})?")) {
            return change;
        }
        return null;
    };
}
