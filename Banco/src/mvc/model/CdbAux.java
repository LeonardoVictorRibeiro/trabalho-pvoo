/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author André
 */
public class CdbAux {
    private ContaCorrente conta;
    private BigDecimal saldo = new BigDecimal("0");
    private LocalDate dataDeposito;

    public LocalDate getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(LocalDate dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
    
    
}
