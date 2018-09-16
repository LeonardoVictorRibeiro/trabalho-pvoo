/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

import java.time.LocalDate;

/**
 *
 * @author leonardo
 */
abstract public class Conta {
    private static long serialC;
    private String titular;
    private long saldo;
    private long limite;
    private String historico;

    public long getSaldo() {
        return saldo;
    }

    public long getLimite() {
        return limite;
    }

    //eu pensei em deixar o adm setar o limite 
    public void setLimite(long limite) {
        this.limite = limite;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }
 
    
    public boolean deposita(long valor){
        if (this.limite < valor){
            return false;
        }
        else{
            this.saldo += valor;
            historico += "\n" + LocalDate.now() + "Depósito de: R$" + valor;
            return true;
        }
    }
    
    public boolean saca(long valor){
        if (this.saldo < valor){
            return false;
        }
        else{
            this.saldo -= valor;
            historico += "\n" + LocalDate.now() + "Saque de: R$" + valor;
            return true;
        }
    }
    
    public boolean transfere(long valor, Conta destino){
        if (destino.limite < valor){
            return false;
        }
        else{
            this.saldo -= valor;
            destino.saldo += valor;
            historico += "\n" + LocalDate.now() + "Transferência de: R$" + valor + " para o titular: " + destino.getTitular();
            return true;
        }
    }
    
    public String estrato(){
        return this.historico;
    }
}
