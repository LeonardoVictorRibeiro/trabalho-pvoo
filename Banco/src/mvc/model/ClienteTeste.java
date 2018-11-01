/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.List;

/**
 *
 * @author leonardo
 */
public class ClienteTeste {
    
    public static void main(String[] args) {
        ClienteDAO clidao = new ClienteDAO();
        
        // Lista todos os clientes no banco
        List<Cliente> clientes = clidao.listar();
        
        for (Object cliente : clientes) {
            System.out.println(cliente);
            
        }
    }
    
}
