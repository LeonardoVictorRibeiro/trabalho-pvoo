/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

import java.util.Scanner;

/**
 *
 * @author Leonardo
 */
public class ClienteCRUD {
    ClienteDAO clienteDAO = new ClienteDAO();
    Scanner scan = new Scanner(System.in);
    
    ClienteCRUD(){
        
        
        
        
        int opcaoEscolhida;
        do{
            opcaoEscolhida = menu();
            switch(opcaoEscolhida){
                case 0:
                    System.out.println("Programa encerrado.");
                    break;
                case 1:
                    cadastrarNovoCliente();
                    break;
                case 2:
                    System.out.println(clienteDAO.listarTodosClientes());
                    break;
                case 3:
                    deletarCliente();
                    break;
                case 4: 
                    buscarCliente();
                    break;
                default: 
                    System.out.println("Opção inválida.");
                    break;
            }
            
        }while(opcaoEscolhida != 0);
        
    }
    
    
    public static void main(String[] args) {
        new ClienteCRUD();
    }
    
    int menu(){
        int opcao;
        StringBuilder menu = new StringBuilder();
        menu.append("-- Menu de opções -- " + "\n");
        menu.append("1. Cadastrar novo cliente" + "\n");
        menu.append("2. Listar todos os clientes" + "\n");
        menu.append("3. Deletar um cliente: " + "\n");
        menu.append("4. Listar um cliente: " + "\n");
        menu.append("Escolha uma opção: ");
        
        System.out.println(menu);
        opcao = Integer.parseInt(scan.nextLine());
        
        return opcao;
    }
    
    void cadastrarNovoCliente(){
        System.out.print("Digite o nome do novo cliente: ");
        String nome = scan.nextLine();
        System.out.print("Digite o CPF do novo cliente: ");
        String cpf = scan.nextLine();
        
        Cliente novoCliente = new Cliente(nome, cpf);
        if(clienteDAO.insereCliente(novoCliente)){
            System.out.println("Cliente cadastrado com sucesso.");
        }else{
            System.out.println("Falha ao cadastrar novo cliente.");
        }
            
    }
    
    void deletarCliente(){
        StringBuilder submenu = new StringBuilder();
        submenu.append("-- Excluir cliente --" + "\n");
        submenu.append("CPF: ");
        System.out.print(submenu);
        String cpf = scan.nextLine();
        Cliente clienteASerExluido = new Cliente(cpf);
        if(clienteDAO.deletaCliente(clienteASerExluido)){
            System.out.println("Cliente excluído com sucesso!");
        }else{
            System.out.println("Cliente não encontrado!");
        }
        
    }
    
    void buscarCliente(){
        StringBuilder submenu = new StringBuilder();
        submenu.append("-- Buscar cliente --" + "\n");
        submenu.append("CPF: ");
        System.out.print(submenu);
        Cliente c = new Cliente(scan.nextLine());
        
        System.out.println(clienteDAO.listarCliente(c));
    }
    
    
    
    
}
