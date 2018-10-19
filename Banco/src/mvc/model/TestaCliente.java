/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Leonardo
 */
public class TestaCliente {
    
    public static void main(String[] args) {
        DateTimeFormatter dtfView = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dtfBanco = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        ClienteDAO cliDAO = new ClienteDAO();
        /*
        Cliente c1 = new Cliente("Leonardo", "11111111", LocalDate.now(), 123456);
        
        System.out.println(c1.getDataNasc());
        System.out.println(c1.getDataNasc().format(dtfView));
        //cliDAO.inserir(c1);
        */
        
        
        List<Cliente> clientes = cliDAO.listar();
        /*
        for (Cliente cliente : clientes) {
            System.out.println("ID: " + cliente.getId());
            System.out.println("CPF: " + cliente.getCpf());
            System.out.println("Nascimento: " + cliente.getDataNasc().format(dtfView));
            System.out.println("Senha: " + cliente.getSenha());
            System.out.println("\n");
        }
        */
        
        System.out.println(cliDAO.buscar(clientes.get(1)).toString());
        
 
        
    }
    
}
