/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

import java.util.Iterator;

/**
 *
 * @author Leonardo
 */
public class ClienteDAO {
    Cliente[] clientes = new Cliente[50];
    int contador;
    
    ClienteDAO(){
      Cliente c1 = new Cliente("Leonardo", "123456789");
      Cliente c2 = new Cliente("Pedro", "123456787");
      Cliente c3 = new Cliente("Maria", "123456788");
      this.insereCliente(c1);
      this.insereCliente(c2);
      this.insereCliente(c3);
    }
    //Funcional
    public int verificaPosicao(){
        
        for ( int i = 0; i < clientes.length; i++){
            if( clientes[i] == null){
                return i;
            }
            contador++;
        }
        
        return -1;
    }
    //Funcional
    public boolean insereCliente(Cliente novoCliente){
        
        int posicao = verificaPosicao();
        if( posicao == -1){
             return false;
        }
        this.clientes[posicao] = novoCliente;
        return true;
       
    }
    //Funcional
    public int encontraCliente(Cliente clienteASerExcluido){
        for( int i = 0; clientes.length > i; i++){
            if(clientes[i] != null && clientes[i].equals(clienteASerExcluido)){
                return i;
            }
        }
        return -1;
    }
    
    //Funcional
    public boolean deletaCliente(Cliente clienteASerExcluido){
        int posicaoCliente = encontraCliente(clienteASerExcluido);
        if(posicaoCliente == -1){
            return false;
        }
        clientes[posicaoCliente] = null;
        return true;
    }
    
    public String listarCliente(Cliente c){
        if(encontraCliente(c) != -1){
            return clientes[encontraCliente(c)].toString();
        }
        return "Não encontrado.";
    }
    
    
    //Funcional
    public String listarTodosClientes(){
        
        String listaClientes = "-- Clientes -- " + "\n";
        
        for ( int i = 0; clientes.length > i; i++) {
            if(clientes[i] != null){
                listaClientes += clientes[i].toString();
            }
        }
        if(listaClientes.contentEquals("-- Clientes -- " + "\n")){
            listaClientes = "Nenhum cliente cadastrado.";
        }
        
        return listaClientes;
    }
    
    
    
    
}
