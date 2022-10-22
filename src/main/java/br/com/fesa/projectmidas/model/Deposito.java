package br.com.fesa.projectmidas.model;

import java.time.LocalDateTime;

public class Deposito {
    private double valorDepositado;
    private LocalDateTime dataDeposito;

    public Deposito() {
    }

    public Deposito(double valorDepositado, LocalDateTime dataDeposito) {
        this.valorDepositado = valorDepositado;
        this.dataDeposito = dataDeposito;
    }

    public double getValorDepositado() {
        return valorDepositado;
    }

    public void setValorDepositado(double valorDepositado) {
        this.valorDepositado = valorDepositado;
    }

    public LocalDateTime getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(LocalDateTime dataDeposito) {
        this.dataDeposito = dataDeposito;
    }
}
