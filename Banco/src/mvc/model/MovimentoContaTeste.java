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
public class MovimentoContaTeste {
    
    public static void main(String[] args) {
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        MovimentoContaCorrenteDAO movdao= new MovimentoContaCorrenteDAO();
        

        ContaCorrente cc1 = ccdao.encontrarConta(new ContaCorrente(2), clienteDAO);
        ContaCorrente cc2 = ccdao.encontrarConta(new ContaCorrente(3), clienteDAO);
        ContaCorrente cc3 = ccdao.encontrarConta(new ContaCorrente(4), clienteDAO);
        ContaCorrente cc4 = ccdao.encontrarConta(new ContaCorrente(5), clienteDAO);
        
        /*
        MovimentoContaCorrente mcc1 = new MovimentoContaCorrente(cc1, 2, "Deposito", new BigDecimal("2000"), LocalDate.now());
        MovimentoContaCorrente mcc2 = new MovimentoContaCorrente(cc1, 2, "Saque", new BigDecimal("3000"), LocalDate.now());
        MovimentoContaCorrente mcc3 = new MovimentoContaCorrente(cc1, 2, "Deposito", new BigDecimal("1000"), LocalDate.now());
        MovimentoContaCorrente mcc4 = new MovimentoContaCorrente(cc1, 2, "Saque", new BigDecimal("7000"), LocalDate.now());
        
        movdao.inserir(cc1, mcc1);
        movdao.inserir(cc2, mcc2);
        movdao.inserir(cc3, mcc3);
        movdao.inserir(cc4, mcc4);
        */

        
        List<MovimentoContaCorrente> mov = movdao.listarMovimentosDeTodasContas(ccdao, clienteDAO);
        
        for(int i = 0; i < mov.size(); i++){
            System.out.println(mov.get(i));
        }
        
        System.out.println("Conta buscada");
        List<MovimentoContaCorrente> mov1conta = movdao.listarMovimentosDeUmaConta(cc3);
        
        for(int i = 0; i < mov1conta.size(); i++){
            System.out.println(mov1conta.get(i));
        }
    }

}
