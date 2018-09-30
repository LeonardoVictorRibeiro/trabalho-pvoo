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
 * @author Leonardo
 */
public class ContaPoupanca {
    private static int serial;
    private int numero;
    private LocalDate dataAbertura;
    private BigDecimal saldo = new BigDecimal("0");
    private BigDecimal limite = new BigDecimal("0");
    private Cliente titular;
    private Extrato[] extrato = new Extrato[50];
    
    public ContaPoupanca(Cliente titular, BigDecimal limite, LocalDate hoje){
        this.numero = serial++;
        this.titular = titular;
        this.dataAbertura = hoje;
        this.limite = this.limite.add(limite);
        Extrato novoExtrato = new Extrato(hoje, 4, new BigDecimal("0"));
        setExtrato(novoExtrato);
    }

    public static int getSerial() {
        return serial;
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
    
    /**
     * Saca um valor da Conta Corrente e transfere para a Conta Poupança
     * @param BigDecimal valor
     * @param LocalDate hoje
     * @param ContaCorrente cc 
     */
    public void depositar(BigDecimal valor, LocalDate hoje, ContaCorrente cc) {
        cc.transferirCP(this, valor, hoje);
        this.saldo = saldo.add(valor);
        Extrato novoExtrato = new Extrato(hoje, 1, valor);
        setExtrato(novoExtrato);
    }
    /**
     * Além de realizar o saque, também salva a data, operação e valor em um array de Extratos
     * @param BigDecimal valor
     * @param LocalDate hoje 
     */
    public void sacar(BigDecimal valor, LocalDate hoje, ContaCorrente cc){
        this.saldo = saldo.subtract(valor);
        cc.depositar(valor, hoje);
        Extrato novoExtrato = new Extrato(hoje, 2, valor);
        setExtrato(novoExtrato);
    }
    
    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }
   
    public StringBuilder getExtrato(){
        StringBuilder info = new StringBuilder();
        for(int i = 0; i < extrato.length; i++){
            if(extrato[i] != null){
                info.append(extrato[i].toString());
            }
        }
        return info.append("\n").append("Saldo atual: ").append(this.saldo);
    }
    
    public void setExtrato(Extrato novoExtrato){
        int i;
        for( i = 0; extrato[i] != null && i < extrato.length; i++){}
        extrato[i] = novoExtrato;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.numero;
        return hash;
    }

    /**
     * Compara o número de duas contas
     * @param obj
     * @return true ou false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContaPoupanca other = (ContaPoupanca) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }

   
    
    
    
    
    @Override
    public String toString() {
        StringBuilder cc = new StringBuilder("\n");
        cc.append("Número: ").append(numero).append("\n");
        cc.append("Aniversário: ").append(dataAbertura).append("\n");
        cc.append("Saldo: ").append(saldo.toString()).append("\n");
        cc.append("Limite: ").append(limite.toString()).append("\n");
        cc.append("Títular: ").append(titular.toString()).append("\n");
        
        return cc.toString();
    }

}
