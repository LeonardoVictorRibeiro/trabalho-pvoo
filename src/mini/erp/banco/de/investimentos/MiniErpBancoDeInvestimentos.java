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
        
        System.out.println("-- Banco de Investimentos --");
        System.out.println("Entre com seus dados");
        System.out.println("CPF: ");
        String cpf = scan.nextLine();
        System.out.println("Senha: ");
        String senha = scan.nextLine();
        Cliente clienteBusca = new Cliente(cpf, senha);
        int posicao = clienteDAO.encontraCliente(clienteBusca);
        if(posicao != -1){
            if(posicao == 0){
                System.out.println("Administrador logado.");
            }else{
                System.out.println("Usuário logado.");
            }
        }else{
            System.out.println("Usuário e/ou senha inválido.");
        }
    }

    
    
    
    public static void main(String[] args) {
        new MiniErpBancoDeInvestimentos();
    }
    
}
