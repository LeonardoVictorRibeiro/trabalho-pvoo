/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.List;

/**
 *
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class ContaPoupancaDepositoTeste {

    public static void main(String[] args) {
        //DAOs necessários
        ClienteDAO cliDAO = new ClienteDAO();
        ContaPoupancaDAO cpDAO = new ContaPoupancaDAO();
        ContaPoupancaDepositoDAO cpDepositoDAO = new ContaPoupancaDepositoDAO();

        //Listar depósitos de uma conta
        ContaPoupanca cp = cpDAO.encontrarConta(new ContaPoupanca(1), cliDAO);
        //Este método retorna uma lista com os depósitos
        List<ContaPoupancaDeposito> cpDepositos = cpDepositoDAO.listarDepositosDeUmaConta(cp);
        //Mostra os depósitos
        for (ContaPoupancaDeposito cpDeposito : cpDepositos) {
            System.out.println(cpDeposito);
        }

        //Listar todos os depósitos ( de todas as contas ) encontrados no banco 
        List<ContaPoupancaDeposito> todosDepositos = cpDepositoDAO.listar(cpDAO, cliDAO);

        for (ContaPoupancaDeposito umDeposito : todosDepositos) {
            System.out.println(umDeposito);
        }
    }

}
