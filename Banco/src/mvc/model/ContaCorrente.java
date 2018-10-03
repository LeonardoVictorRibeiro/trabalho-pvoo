/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 *
 * @author Leonardo
 */
public class ContaCorrente {
    private static int serial;
    private long numero;
    private LocalDate dataAbertura;
    private BigDecimal saldo = new BigDecimal("0");
    private BigDecimal limite = new BigDecimal("0");
    private Cliente titular;
    private Movimentacoes[] extrato = new Movimentacoes[50];
    /**
     * Cria uma nova CC e seta o extrato inicíal
     * @param Cliente titular
     * @param BigDecimal valor
     * @param BigDecimal limite
     * @param BigDecimal hoje 
     */
    public ContaCorrente(Cliente titular,BigDecimal valor, BigDecimal limite, LocalDate hoje){
        this.numero = serial++;
        this.titular = titular;
        this.dataAbertura = hoje;
        this.saldo = saldo.add(valor);
        this.limite = this.limite.add(limite);
        Movimentacoes novoExtrato = new Movimentacoes(hoje, 1, valor);
        setExtrato(novoExtrato);
    }

    public static int getSerial() {
        return serial;
    }
    
    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }
    
    public boolean setSaldo(BigDecimal quantia){
        this.saldo = quantia;
        return true;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal valor, LocalDate hoje) {
        this.saldo = saldo.add(valor);
        Movimentacoes novoExtrato = new Movimentacoes(hoje, 1, valor);
        setExtrato(novoExtrato);
    }
    
    public boolean adicionaJuros(){
        
        return false;
    }
    
    public boolean pagarManutencao(LocalDate dataAtual){
        
        if(dataAtual.getDayOfMonth() == 15){
            BigDecimal taxaManutencao = new BigDecimal("20");
            this.saldo = saldo.subtract(taxaManutencao);
            Movimentacoes novoExtrato = new Movimentacoes(dataAtual, 5, taxaManutencao);
            setExtrato(novoExtrato);
            return true;
        }
        return false;
    }
    
    /**
     * Além de realizar o saque, também salva a data, operação e valor em um array de Extratos
     * @param BigDecimal valor
     * @param LocalDate hoje 
     */
    public void sacar(BigDecimal valor, LocalDate hoje){
        
        this.saldo = saldo.subtract(valor);
        Movimentacoes novoExtrato = new Movimentacoes(hoje, 2, valor);
        setExtrato(novoExtrato);
    }
    public void transferirCP(ContaPoupanca cp, BigDecimal valor, LocalDate hoje){
        this.saldo = saldo.subtract(valor);
        Movimentacoes novoExtrato = new Movimentacoes(hoje, 3, valor);
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
    
    public void setExtrato(Movimentacoes novoExtrato){
        int i;
        for( i = 0; extrato[i] != null && i < extrato.length; i++){}
        extrato[i] = novoExtrato;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.numero ^ (this.numero >>> 32));
        return hash;
    }

   
    /**
     * Compara o número de duas contas
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
        final ContaCorrente other = (ContaCorrente) obj;
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
