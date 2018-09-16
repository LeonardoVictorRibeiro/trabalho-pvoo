/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

/**
 *
 * @author André
 */
public class ContaDAO {
    Conta[] contas = new Conta[50];
    int contador;
    
    
    public int verificaPosicao(){
        
        for ( int i = 0; i < contas.length; i++){
            if( contas[i] == null){
                return i;
            }
            contador++;
        }
        
        return -1;
    }
    
    public boolean insereConta(Conta novaConta){
        
        int posicao = verificaPosicao();
        if( posicao == -1){
             return false;
        }
        this.contas[posicao] = novaConta;
        return true;
       
    }

}
