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
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class ContaPoupancaTeste {

    public static void main(String[] args) {
        //DAOs necessários para manipular uma conta
        ClienteDAO clienteDAO = new ClienteDAO();
        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();

        //Busca um cliente no banco de dados para ter atrelado a conta
        Cliente cliente1 = clienteDAO.buscar(new Cliente(1));

        //Cria a conta com saldo ZERO e insere no banco
        ContaPoupanca cp1 = new ContaPoupanca(cliente1, BigDecimal.ZERO);

        //Busca a conta no banco usando o ID ( usei uma instância com o id ao invés de um variável) 
        ContaPoupanca cpEncontrada = contaPoupancaDAO.encontrarConta(new ContaPoupanca(1), clienteDAO);

        //Modificar a conta
        //Busco a conta a ser modificada
        ContaPoupanca contaAEditar = contaPoupancaDAO.encontrarConta(new ContaPoupanca(1), clienteDAO);
        //Edito a conta
        contaAEditar.setSaldo(new BigDecimal(1000)); // Coloquei 1.000 de saldo
        //Envio para o servidor
        contaPoupancaDAO.atualizar(contaAEditar);

        //Procuro uma conta e deleto
        ContaPoupanca cpDeleta = new ContaPoupanca(1);
        contaPoupancaDAO.deletar(cpDeleta);

        //Coloco todas as contas encontras em uma lista
        List<ContaPoupanca> contas = contaPoupancaDAO.listar(clienteDAO);

        //Lista todas as contas no banco de dados
        for (ContaPoupanca conta : contas) {
            System.out.println(conta);
        }

    }

}
