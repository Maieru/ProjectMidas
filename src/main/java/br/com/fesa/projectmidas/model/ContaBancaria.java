package br.com.fesa.projectmidas.model;

import br.com.fesa.projectmidas.negocio.SecurityHelper;
import java.util.List;

public class ContaBancaria {
    private Integer codigo;
    private Integer numero;
    private Agencia agencia;
    private String senha;
    private double saldo;
    private String correntista;
    private String CPF;
    private List<CartaoCredito> cartoes;
    private CarteiraInvestimento carteira;

    public ContaBancaria() {
    }

    public ContaBancaria(Integer numero) {
        this.numero = numero;
    }

    public ContaBancaria(Integer numero, Agencia agencia) {
        this.numero = numero;
        this.agencia = agencia;
    }

    public ContaBancaria(Integer numero, Agencia agencia, String senha, String correntista, String CPF) {
        this.numero = numero;
        this.agencia = agencia;
        this.senha = senha;
        this.correntista = correntista;
        this.CPF = CPF;
        this.saldo = 0;
        this.carteira = null;
        this.cartoes = null;
    }
    
    public ContaBancaria(Integer codigo, Integer numero, Agencia agencia, String senha, double saldo, String correntista, String CPF, List<CartaoCredito> cartoes, CarteiraInvestimento carteira) {
        this.codigo = codigo;
        this.numero = numero;
        this.agencia = agencia;
        this.senha = senha;
        this.saldo = saldo;
        this.correntista = correntista;
        this.CPF = CPF;
        this.cartoes = cartoes;
        this.carteira = carteira;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getSaldo() {
        return saldo;
    }
    
    public String getCorrentista() {
        return correntista;
    }

    public void setCorrentista(String correntista) {
        this.correntista = correntista;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public List<CartaoCredito> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoCredito> cartoes) {
        this.cartoes = cartoes;
    }

    public CarteiraInvestimento getCarteira() {
        return carteira;
    }

    public void setCarteira(CarteiraInvestimento carteira) {
        this.carteira = carteira;
    }

    public void modificaSaldo(double valorASerAdicionado){
        this.saldo += valorASerAdicionado;
    }
    
    public void encriptaSenha(){
        this.senha = SecurityHelper.getSHA512(this.getSenha());
    }
}
