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
public class ContaCorrente extends Conta{

    ContaCorrente(String nome) {
    this.setTitular(nome);
    }
    
    
    
    
    
    
    
    
    public ContaCorrente criaCCorrente(String nome){
        ContaCorrente novaConta = new ContaCorrente(nome);
        return novaConta;
    }
    
}
