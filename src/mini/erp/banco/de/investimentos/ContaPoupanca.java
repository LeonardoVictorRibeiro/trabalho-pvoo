/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

/**
 *
 * @author leonardo
 */
public class ContaPoupanca extends Conta{
    
    ContaPoupanca(String nome) {
    this.setTitular(nome);
    }
    
    
    
    
    public ContaPoupanca criaCPoupanca(String nome){
        ContaPoupanca novaConta = new ContaPoupanca(nome);
        return novaConta;
    }
}
