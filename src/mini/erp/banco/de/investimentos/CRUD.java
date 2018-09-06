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
public class CRUD {
    ClienteDAO clienteDAO = new ClienteDAO();
    Scanner scan = new Scanner(System.in);
    
    CRUD(){
        
        
        
        
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
                default: 
                    System.out.println("Opção inválida.");
                    break;
            }
            
        }while(opcaoEscolhida != 0);
        
    }
    
    
    public static void main(String[] args) {
        new CRUD();
    }
    
    int menu(){
        int opcao;
        String menu = "-- Menu de opções -- " + "\n";
        menu += "1. Cadastrar novo cliente" + "\n";
        menu += "2. Listar todos os clientes" + "\n";
        menu += "Escolha uma opção: ";
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
    
    
}
