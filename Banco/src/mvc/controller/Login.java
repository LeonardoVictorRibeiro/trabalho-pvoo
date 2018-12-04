/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import mvc.model.Cliente;
import mvc.model.ClienteDAO;

/**
 *
 * @author leonardo
 */
public class Login {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private static Cliente logado = null; 
    
    public Cliente logar(String usuario, int senha){
        Cliente cliente = new Cliente(usuario);
        
        cliente = clienteDAO.buscarPorCPF(cliente);
        
        if(cliente != null && cliente.getSenha() == senha){
            logado = cliente;
            return cliente;
        }
        return null;
    }
    
    public void deslogar(){
        this.logado = null;
    }
    
    public Cliente getLogado(){
        return this.logado;
    }
    
    public boolean verificaAdm(){
        if(logado != null && logado.getId() == 1){
            return true;
        }
        
        return false;
    }
    
}
