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
        ContaPoupancaDAO cpdao = new ContaPoupancaDAO();
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        ContaPoupancaTransaction cptrans = new ContaPoupancaTransaction();
        ClienteDAO clidao = new ClienteDAO();
        
        
        Cliente cliente1 = clidao.buscar(new Cliente(Long.parseLong("1")));
        Cliente cliente2 = clidao.buscar(new Cliente(Long.parseLong("2")));
        Cliente cliente3 = clidao.buscar(new Cliente(Long.parseLong("3")));
        Cliente cliente4 = clidao.buscar(new Cliente(Long.parseLong("4")));
        
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
        
        ContaPoupanca cp6 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("6")), clidao);
        ContaPoupanca cp7 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("7")), clidao);
        ContaPoupanca cp8 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("8")), clidao);
        ContaPoupanca cp9 = cpdao.encontrarConta(new ContaPoupanca(Long.parseLong("9")), clidao);
        
        ContaCorrente cc6 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("3")), clidao);
        ContaCorrente cc7 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("4")),clidao);
        ContaCorrente cc8 = ccdao.encontrarConta(new ContaCorrente(Long.parseLong("2")),clidao);
        
        
        
        BigDecimal valor1 = new BigDecimal("100");
        BigDecimal valor2 = new BigDecimal("200");
        BigDecimal valor3 = new BigDecimal("300");
        BigDecimal valor4 = new BigDecimal("400");
        
        cc6.sacar(valor1);
        cc7.sacar(valor2);
        cc8.sacar(valor3);
        
        
        cp6.depositar(valor1);
        cp7.depositar(valor2);
        cp8.depositar(valor3);
        
        ContaPoupancaDeposito cpd1 = new ContaPoupancaDeposito(cp6, valor1, LocalDate.now(), LocalDate.now().plusDays(30));
        ContaPoupancaDeposito cpd2 = new ContaPoupancaDeposito(cp7, valor2, LocalDate.now(), LocalDate.now().plusDays(36));
        ContaPoupancaDeposito cpd3 = new ContaPoupancaDeposito(cp8, valor3, LocalDate.now(), LocalDate.now().plusDays(40));
        ContaPoupancaDeposito cpd4 = new ContaPoupancaDeposito(cp9, valor4, LocalDate.now(), LocalDate.now().plusDays(32));
        
         List<ContaPoupancaDeposito> contas = cptrans.listar(cpdao, clidao);
        System.out.println("POUPANÇA");
        for (ContaPoupancaDeposito conta : contas) {
            System.out.println(conta);
        }
        System.out.println("CORRENTE");
        List<ContaCorrente> contascc = ccdao.listar(clidao);
        for (ContaCorrente contaCorrente : contascc) {
            System.out.println(contaCorrente);
        }
        
        
        cptrans.depositar(cpd1, cc6);
        cptrans.depositar(cpd2, cc7);
        cptrans.depositar(cpd3, cc8);
        //cptrans.depositar(cpd4);
        
   
        
        
        /*
        List<ContaPoupancaDeposito> contas = cptrans.listarDepositosDeUmaConta(cp8);
        
        for (ContaPoupancaDeposito conta : contas) {
            System.out.println(conta);
        }
        */
    }

}
