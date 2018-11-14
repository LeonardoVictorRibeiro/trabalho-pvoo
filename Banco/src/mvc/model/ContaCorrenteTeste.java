/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Leonardo
 */
public class ContaCorrenteTeste {

    public static void main(String[] args) {
        //DAOs usados para manipular contas
        ClienteDAO cliDAO = new ClienteDAO();
        ContaCorrenteDAO ccDAO = new ContaCorrenteDAO();

        //Busca o cliente que será titular da conta
        Cliente cliente1 = new Cliente(1);
        Cliente clienteTitular = cliDAO.buscar(cliente1);
        //Cria uma conta com saldo inicial R$0.00
        ContaCorrente cc1 = new ContaCorrente(clienteTitular, BigDecimal.ZERO);

        //Busca a conta usando o ID
        ContaCorrente encontraCC = new ContaCorrente(1);
        ContaCorrente contaEncontrada = ccDAO.encontrarConta(encontraCC, cliDAO);

        //Busca a conta usando o ID do cliente
        Cliente cliente2 = new Cliente(2);
        ContaCorrente contaCliente2 = ccDAO.encontrarConta(cliente2, cliDAO);

        //Modifica uma conta
        ContaCorrente procuraCC = new ContaCorrente(1);
        ContaCorrente contaCC = ccDAO.encontrarConta(procuraCC, cliDAO);
        //Edita conta

        contaCC.setSaldo(new BigDecimal("30000"));
        ccDAO.atualizar(contaCC);

        //Lista todas as contas
        List<ContaCorrente> contas = ccDAO.listar(cliDAO);

        for (ContaCorrente conta : contas) {
            System.out.println(conta);
        }

    }

}
