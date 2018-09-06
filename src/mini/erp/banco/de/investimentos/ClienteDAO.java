/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

/**
 *
 * @author Leonardo
 */
public class ClienteDAO {
    Cliente[] clientes = new Cliente[50];
    int contador;
    
    ClienteDAO(){
      Cliente c1 = new Cliente("Leonardo", "123456789");
      Cliente c2 = new Cliente("Pedro", "123456789");
      Cliente c3 = new Cliente("Maria", "123456789");
      
  
    // System.out.println("Clientes cadastrados: " + contador);

    }
    
    public int verificaPosicao(){
        
        for ( int i = 0; i < clientes.length; i++){
            if( clientes[i] == null){
                return i;
            }
            contador++;
        }
        
        return -1;
    }
    
    public boolean insereCliente(Cliente novoCliente){
        
        int posicao = verificaPosicao();
        if( posicao == -1){
             return false;
        }
        this.clientes[posicao] = novoCliente;
        return true;
       
    }
    
    
}
