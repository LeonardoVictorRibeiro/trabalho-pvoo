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
public class MiniErpBancoDeInvestimentos {

    Scanner scan = new Scanner(System.in);
    ClienteDAO clienteDAO = new ClienteDAO();

    public MiniErpBancoDeInvestimentos() {
        int sucesso;
        do {
            sucesso = telaLogin();
        } while (sucesso == -1);
        if (sucesso == 0) {
            telaAdm();
        } else {
            telaCliente();
        }

    }

    public int telaLogin() {
        System.out.println("-- Banco de Investimentos --");
        System.out.println("Entre com seus dados");
        System.out.print("CPF: ");
        String cpf = scan.nextLine();
        System.out.print("Senha: ");
        String senha = scan.nextLine();
        Cliente clienteBusca = new Cliente(cpf, senha);
        // retorna a posição do cliente
        int posicao = clienteDAO.encontraCliente(clienteBusca);
        // verifica se a posição existe
        if (posicao != -1) {
            // Verifica se é cliente ou adm
            if (posicao == 0) {
                System.out.println("Administrador logado.");
                return 0;
            } else {
                System.out.println("Usuário logado.");
                return 1;
            }
        } else {
            System.out.println("Usuário e/ou senha inválido.");
        }
        return -1;
    }
    
    public void telaAdm(){
        System.out.println("Tela do ADM!");
    }
    
    public void telaCliente(){
        System.out.println("Tela do cliente!");
        
    }

    public static void main(String[] args) {
        new MiniErpBancoDeInvestimentos();
    }

}
