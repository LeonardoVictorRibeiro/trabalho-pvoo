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

    ClienteCRUD() {
        menuPrincipal();
        
    }

    public static void main(String[] args) {
        new ClienteCRUD();
    }

    int menu() {
        int opcao;
        StringBuilder menu = new StringBuilder();
        menu.append("-- CRUD DE CLIENTES -- " + "\n");
        menu.append("1. Cadastrar novo cliente" + "\n");
        menu.append("2. Listar todos os clientes" + "\n");
        menu.append("3. Deletar um cliente: " + "\n");
        menu.append("4. Listar um cliente: " + "\n");
        menu.append("5. Alterar um cliente: " + "\n");
        menu.append("Digite uma opção: ");

        System.out.println(menu);
        opcao = Integer.parseInt(scan.nextLine());

        return opcao;
    }
    
    void menuPrincipal(){
        int opcaoEscolhida;
        do {
            opcaoEscolhida = menu();
            switch (opcaoEscolhida) {
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
                case 5:
                    alterarCliente();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcaoEscolhida != 0);

    }

    void cadastrarNovoCliente() {
        System.out.print("Digite o nome do novo cliente: ");
        String nome = scan.nextLine();
        System.out.print("Digite o CPF do novo cliente: ");
        String cpf = scan.nextLine();

        Cliente novoCliente = new Cliente(nome, cpf);
        if (clienteDAO.insereCliente(novoCliente)) {
            System.out.println("Cliente cadastrado com sucesso.");
        } else {
            System.out.println("Falha ao cadastrar novo cliente.");
        }

    }

    void deletarCliente() {
        StringBuilder submenu = new StringBuilder();
        submenu.append("-- Excluir cliente --" + "\n");
        submenu.append("CPF: ");
        System.out.print(submenu);
        String cpf = scan.nextLine();
        Cliente clienteASerExluido = new Cliente(cpf);
        if (clienteDAO.deletaCliente(clienteASerExluido)) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Cliente não encontrado!");
        }

    }

    void buscarCliente() {
        StringBuilder submenu = new StringBuilder();
        submenu.append("-- Buscar cliente --" + "\n");
        submenu.append("CPF: ");
        System.out.print(submenu);
        Cliente c = new Cliente(scan.nextLine());

        System.out.println(clienteDAO.listarCliente(c));
    }

    void alterarCliente() {
        StringBuilder submenu = new StringBuilder();
        submenu.append("-- Alterar cliente --" + "\n");
        submenu.append("CPF: ");
        System.out.print(submenu);
        Cliente c = new Cliente(scan.nextLine());
        if (clienteDAO.encontraCliente(c) != -1) {
            int opcao;
            do {
                opcao = menuAlteracao();
                switch (opcao) {
                    case 1:
                        System.out.println("Nome:");
                        clienteDAO.clientes[clienteDAO.encontraCliente(c)].setNome(scan.nextLine());
                        break;
                    case 2:
                        System.out.println("CPF:");
                        clienteDAO.clientes[clienteDAO.encontraCliente(c)].setCpf(scan.nextLine());
                        break;
                    case 3:
                        System.out.println("Senha:");
                        clienteDAO.clientes[clienteDAO.encontraCliente(c)].setSenha(scan.nextLine());
                        break;
                    case 4:
                        System.out.println("Retornando ao menu principal.");
                        break;
                    default:
                        System.out.println("Opção inválida.");

                }
            } while (opcao != 4);
        }else{
            System.out.println("Cliente não encontrado.");
        }
        

    }

    int menuAlteracao() {
        StringBuilder menuAlteracao = new StringBuilder();
        menuAlteracao.append("Quais dados deseja alterar?" + "\n");
        menuAlteracao.append("1.Nome" + "\n");
        menuAlteracao.append("2.CPF" + "\n");
        menuAlteracao.append("3.Senha" + "\n");
        menuAlteracao.append("4. Finalizar" + "\n");
        menuAlteracao.append("Digite uma opção:");
        System.out.println(menuAlteracao);

        return Integer.parseInt(scan.nextLine());
    }

}