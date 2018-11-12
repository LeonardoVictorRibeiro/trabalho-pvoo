/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class ContaPoupancaTransTeste {

    
    public static void main(String[] args) {
        
        BigDecimal valor1 = new BigDecimal("100");
        BigDecimal valor2 = new BigDecimal("200");
        BigDecimal valor3 = new BigDecimal("300");
        BigDecimal valor4 = new BigDecimal("400");
        BigDecimal valor5 = new BigDecimal("500");
        
        
        //Cliente
        ClienteDAO clidao = new ClienteDAO();
        
        Cliente cliente1 = clidao.buscar(new Cliente(Long.parseLong("1")));
        Cliente cliente2 = clidao.buscar(new Cliente(Long.parseLong("2")));
        Cliente cliente3 = clidao.buscar(new Cliente(Long.parseLong("3")));
        Cliente cliente4 = clidao.buscar(new Cliente(Long.parseLong("4")));
        Cliente cliente5 = clidao.buscar(new Cliente(Long.parseLong("5")));
        
        //Poupanca
        ContaPoupancaDAO cpdao = new ContaPoupancaDAO();
        ContaPoupancaDepositoDAO cpdDAO = new ContaPoupancaDepositoDAO();
        ContaPoupancaTransaction cptrans = new ContaPoupancaTransaction();
        
        ContaPoupanca cp1 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("1")), clidao);
        ContaPoupanca cp2 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("2")), clidao);
        ContaPoupanca cp3 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("3")), clidao);
        ContaPoupanca cp4 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("4")), clidao);
        ContaPoupanca cp5 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("5")), clidao);
        
           
        cp1.depositar(valor1);
        cp2.depositar(valor2);
        cp3.depositar(valor3);
        cp4.depositar(valor4);
        cp5.depositar(valor5);
        
        ContaPoupancaDeposito cpd1 = new ContaPoupancaDeposito(cp1, valor1, LocalDate.now(), LocalDate.now().plusDays(30));
        ContaPoupancaDeposito cpd2 = new ContaPoupancaDeposito(cp2, valor2, LocalDate.now(), LocalDate.now().plusDays(36));
        ContaPoupancaDeposito cpd3 = new ContaPoupancaDeposito(cp3, valor3, LocalDate.now(), LocalDate.now().plusDays(40));
        ContaPoupancaDeposito cpd4 = new ContaPoupancaDeposito(cp4, valor4, LocalDate.now(), LocalDate.now().plusDays(32));
        ContaPoupancaDeposito cpd5 = new ContaPoupancaDeposito(cp5, valor5, LocalDate.now(), LocalDate.now().plusDays(32));
        
        //Corrente 
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        MovimentoContaCorrenteDAO movDAO = new MovimentoContaCorrenteDAO();
        
        ContaCorrente cc1 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("1")), clidao);
        ContaCorrente cc2 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("2")),clidao);
        ContaCorrente cc3 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("3")),clidao);
        ContaCorrente cc4 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("4")),clidao);
        ContaCorrente cc5 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("5")),clidao);
        
        cc1.sacar(valor1);
        cc2.sacar(valor2);
        cc3.sacar(valor3);
        cc4.sacar(valor4);
        cc5.sacar(valor5);
        
        MovimentoContaCorrente mov1 = new MovimentoContaCorrente(cc1, 2, "Depósito para a poupança", valor1, LocalDate.now());
        MovimentoContaCorrente mov2 = new MovimentoContaCorrente(cc2, 2, "Depósito para a poupança", valor2, LocalDate.now());
        MovimentoContaCorrente mov3 = new MovimentoContaCorrente(cc3, 2, "Depósito para a poupança", valor3, LocalDate.now());
        MovimentoContaCorrente mov4 = new MovimentoContaCorrente(cc4, 2, "Depósito para a poupança", valor4, LocalDate.now());
        MovimentoContaCorrente mov5 = new MovimentoContaCorrente(cc5, 2, "Depósito para a poupança", valor5, LocalDate.now());
        
        //Realiza as transações
        cptrans.depositar(cpd1, cc1, mov1);
        cptrans.depositar(cpd2, cc2, mov2);
        cptrans.depositar(cpd3, cc3, mov3);
        cptrans.depositar(cpd4, cc4, mov4);
        cptrans.depositar(cpd5, cc5, mov5);
     
        
        /*
        ContaPoupanca cp1 = new ContaPoupanca(cliente1, BigDecimal.ZERO);
        ContaPoupanca cp2 = new ContaPoupanca(cliente2, BigDecimal.ZERO);
        ContaPoupanca cp3 = new ContaPoupanca(cliente3, BigDecimal.ZERO);
        ContaPoupanca cp4 = new ContaPoupanca(cliente4, BigDecimal.ZERO);
        */
        
        
        /*
        cpdao.inserir(cp1);
        cpdao.inserir(cp2);
        cpdao.inserir(cp3);
        cpdao.inserir(cp4);
        */
        
        
        
       
        
        
        
        
        
       
        
     
        
        
        
        List<ContaPoupancaDeposito> contas = cpdDAO.listar(cpdao, clidao);
        System.out.println("POUPANÇA");
        for (ContaPoupancaDeposito conta : contas) {
            System.out.println(conta);
        }
        
        
        
        System.out.println("CORRENTE");
        List<ContaCorrente> contascc = ccdao.listar(clidao);
        for (ContaCorrente contaCorrente : contascc) {
            System.out.println(contaCorrente);
        }
        
        System.out.println("Movimentações Poupanca");
        List<ContaPoupancaDeposito> depositos = cpdDAO.listar(cpdao, clidao);
        
        System.out.println("Movimentações Corrente");
        List<MovimentoContaCorrente> movimentacoes = movDAO.listarMovimentosDeTodasContas(ccdao, clidao);
        
        
        //cptrans.depositar(cpd1, cc6);
        //cptrans.depositar(cpd2, cc7);
        //cptrans.depositar(cpd3, cc8);
        //cptrans.depositar(cpd4);
        
   
        
        
        /*
        List<ContaPoupancaDeposito> contas = cptrans.listarDepositosDeUmaConta(cp8);
        
        for (ContaPoupancaDeposito conta : contas) {
            System.out.println(conta);
        }
        */
    }

}
