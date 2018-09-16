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
      Cliente adm = new Cliente("Leonardo", "123456789", "12345");
      Cliente c2 = new Cliente("Pedro", "123456787", "12346");
      Cliente c3 = new Cliente("Maria", "123456788", "12347");
      Cliente c4 = new Cliente("Raissa", "123456783", "12347");
      Cliente c5 = new Cliente("Maria", "123456781", "12347");
      this.insereAdministrador(adm);
      this.insereCliente(c2);
      this.insereCliente(c3);
      this.insereCliente(c4);
      this.insereCliente(c5);
    }
    //Encontra uma posição está vazia
    public int verificaPosicao(){
        
        for ( int i = 0; i < clientes.length; i++){
            if( clientes[i] == null){
                return i;
            }
            contador++;
        }
        
        return -1;
    }
    public void insereAdministrador(Cliente novoCliente){
        this.clientes[0] = novoCliente;
    }
    //Insere um novo cliente. Se existe um espaço vazio entre 2 clientes, então o novo cliente será criado nessa posição
    public boolean insereCliente(Cliente novoCliente){
        
        int posicao = verificaPosicao();
        if( posicao == -1){
             return false;
        }
        this.clientes[posicao] = novoCliente;
        return true;
       
    }
    //Encontra a posição do cliente
    public int encontraCliente(Cliente clienteASerExcluido){
        for( int i = 0; clientes.length > i; i++){
            if(clientes[i] != null && clientes[i].equals(clienteASerExcluido)){
                return i;
            }
        }
        return -1;
    }
    
    //Recebe um cliente como parâmetro e "exclui" - null
    public boolean deletaCliente(Cliente clienteASerExcluido){
        int posicaoCliente = encontraCliente(clienteASerExcluido);
        
        if(posicaoCliente == -1 || posicaoCliente == 0){
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
