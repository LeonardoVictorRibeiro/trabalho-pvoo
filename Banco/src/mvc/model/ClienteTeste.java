/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class ClienteTeste {
    
    public static void main(String[] args) {
        //Formata uma data para um padrão válido
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        //DAO usado para manipular o banco
        ClienteDAO clidao = new ClienteDAO();
        
        //Insere um novo cliente no banco
        String nascimento = "02/02/1992"; //Data de nascimento do cliente
        Cliente novoCliente = new Cliente("Pedro", "11111111111", LocalDate.parse(nascimento, dtf), 123456);
        clidao.inserir(novoCliente);
        
        //Busca um cliente no banco através do ID
        Cliente buscaCliente = new Cliente(1); //Cliente a ser procurado
        Cliente encontradoCliente = clidao.buscar(buscaCliente);
        
        //Mostra os dados do cliente encontrado
        System.out.println(encontradoCliente);
        
        //Recebe todos os clientes encontrados no banco em uma lista
        List<Cliente> clientes = clidao.listar();
        
        //Percore a lista
        for (Cliente cliente : clientes) {
            System.out.println("Id: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Nascimento: " + cliente.getDataNasc().format(dtf));
            System.out.println("Cpf: " + cliente.getCpf());
        }
    }
    
}
