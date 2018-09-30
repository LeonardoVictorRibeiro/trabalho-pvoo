package mvc.model;
import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leonardo
 */
public class Extrato {
    private LocalDate data;
    private int operacao;
    private BigDecimal valor = new BigDecimal("0");
    
    public Extrato(LocalDate data, int operacao, BigDecimal valor){
        this.data = data;
        this.operacao = operacao;
        this.valor = this.valor.add(valor);
    }
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public StringBuilder getOperacao() {
        StringBuilder op = new StringBuilder();
        if(this.operacao == 1){
            op.append("Deposito");
        }
        if(this.operacao == 2){
            op.append("Saque");
        }
        if(this.operacao == 3){
            op.append("Transferência para CP");
        }
        if(this.operacao == 4){
            op.append("Abertura da conta.");
        }
        
        return op;
    }


    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        StringBuilder extrato = new StringBuilder("\n");
        extrato.append("-- Extrato -- \n");
        extrato.append("Data: ").append(data.toString()).append("\n");
        extrato.append("Operação: ").append(getOperacao()).append("\n");
        extrato.append("Valor: ").append(valor.toString());
        return extrato.toString();
    }
                                                                                                                                                       
   
}
