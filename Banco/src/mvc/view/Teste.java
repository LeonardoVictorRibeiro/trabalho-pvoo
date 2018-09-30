/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import mvc.model.Cliente;
import mvc.model.ContaCorrente;
import mvc.model.ContaPoupanca;

/**
 *
 * @author Leonardo
 */
public class Teste {
    public static void main(String[] args) {
        LocalDate hoje = LocalDate.now();
        
        
        Cliente c1 = new Cliente("Leonardo", "111111", 123456);
        Cliente c2 = new Cliente("Rogerio", "123131", 112312);
        
        ContaCorrente cc1 = new ContaCorrente(c1, new BigDecimal("2000"), new BigDecimal("6000"), hoje);
        
        ContaCorrente cc2 = new ContaCorrente(c2, new BigDecimal("1000"), new BigDecimal("2000"), hoje);
        cc2.depositar(new BigDecimal("1000"), hoje);
        cc2.depositar(new BigDecimal("3000"), hoje.plusDays(30));
        
        System.out.println("Conta Poupança");
        ContaPoupanca cp1 = new ContaPoupanca(c2, new BigDecimal("60000"), hoje);
        cp1.depositar(new BigDecimal("3000"), hoje, cc2);
        System.out.println(cp1.getExtrato());
        
        System.out.println("Conta Corrente");
        System.out.println(cc2.getExtrato());
        }
}
