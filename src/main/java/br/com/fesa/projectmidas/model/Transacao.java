package br.com.fesa.projectmidas.model;

import br.com.fesa.projectmidas.dataaccessobject.ContaBancariaDAO;
import br.com.fesa.projectmidas.dataaccessobject.TransacaoDAO;
import br.com.fesa.projectmidas.exception.NegocioException;
import br.com.fesa.projectmidas.exception.PersistenciaException;
import java.time.LocalDateTime;

public class Transacao {

    private Integer codigo;
    private ContaBancaria origem;
    private ContaBancaria destino;
    private LocalDateTime dataTransacao;
    private double valor;
    private String descricao;
    private TipoTransacao tipoTransacao;
    private boolean pagoComCredito;

    public Transacao() {
    }

    public Transacao(Integer codigo, ContaBancaria origem, ContaBancaria destino, LocalDateTime dataTransacao, double valor, String descricao, TipoTransacao tipoTransacao, boolean pagoComCredito) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.dataTransacao = dataTransacao;
        this.valor = valor;
        this.descricao = descricao;
        this.tipoTransacao = tipoTransacao;
        this.pagoComCredito = pagoComCredito;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public ContaBancaria getOrigem() {
        return origem;
    }

    public void setOrigem(ContaBancaria origem) {
        this.origem = origem;
    }

    public ContaBancaria getDestino() {
        return destino;
    }

    public void setDestino(ContaBancaria destino) {
        this.destino = destino;
    }

    public LocalDateTime getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDateTime dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public boolean isPagoComCredito() {
        return pagoComCredito;
    }

    public void setPagoComCredito(boolean pagoComCredito) {
        this.pagoComCredito = pagoComCredito;
    }

    public boolean transaciona() throws NegocioException, PersistenciaException {
        switch (this.getTipoTransacao()) {
            case DEPOSITO:
                break;
            case SAQUE:
                break;
            case TRANSFERENCIA_PIX:
            case TRANSFERENCIA_TED:
            case TRANSFERENCIA_DOC:
                return fazTransferencia();
            case PAGAMENTO_BOLETO:
                break;
            case PAGAMENTO_PIX:
                break;
            default:
                throw new AssertionError(this.getTipoTransacao().name());

        }
        
        return false;
    }
    
    private boolean fazTransferencia() throws NegocioException, PersistenciaException{
        if (this.getOrigem().getSaldo() < this.getValor()){
            throw new NegocioException("A conta de origem não possui o saldo suficiente para realizar a transação!");
        }
        
        this.getOrigem().modificaSaldo(-this.getValor());
        this.getDestino().modificaSaldo(this.getValor());
        
        this.setDataTransacao(LocalDateTime.now());
        this.setDescricao("TRANSF " + this.getDestino().getCorrentista());
        
        ContaBancariaDAO daoContaBancaria = new ContaBancariaDAO();
        TransacaoDAO daoTransacao = new TransacaoDAO();
        
        daoContaBancaria.alterar(this.getOrigem());
        daoContaBancaria.alterar(this.getDestino());
        daoTransacao.inserir(this);
        
        return true;
    }
}
