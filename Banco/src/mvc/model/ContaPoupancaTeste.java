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
public class ContaPoupancaTeste {
    
    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        ContaPoupancaDAO contaPoupancaDAO = new ContaPoupancaDAO();
        
        Cliente c4 = clienteDAO.buscar(new Cliente(Long.parseLong("4")));
        Cliente c3 = clienteDAO.buscar(new Cliente(Long.parseLong("3")));
        Cliente c2 = clienteDAO.buscar(new Cliente(Long.parseLong("2")));
        
        ContaPoupanca cp1 = new ContaPoupanca(c4, new BigDecimal("200"));
        //contaPoupancaDAO.inserir(cp1);
        ContaPoupanca cp2 = new ContaPoupanca(c3, new BigDecimal("500"));
        ContaPoupanca cp3 = new ContaPoupanca(c2, new BigDecimal("2200"));
        
        //contaPoupancaDAO.inserir(cp2);
        //contaPoupancaDAO.inserir(cp3);
        
       
        //contaPoupancaDAO.deletar(new ContaPoupanca(3));
        


        ContaPoupanca contaBusca = contaPoupancaDAO.encontrarConta(new ContaPoupanca(5), clienteDAO);
        System.out.println(contaBusca);
        contaBusca.depositar(new BigDecimal("200"));
        
        
        
        contaPoupancaDAO.atualizar(contaBusca);
        contaBusca = contaPoupancaDAO.encontrarConta(new ContaPoupanca(5), clienteDAO);
        System.out.println(contaBusca);
    }

}
