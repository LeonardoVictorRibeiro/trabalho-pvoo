/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class ContaPoupancaDepositoTeste {
    
    public static void main(String[] args) {
        ClienteDAO cliDAO = new ClienteDAO();
        ContaPoupancaDAO cpDAO = new ContaPoupancaDAO();
        
        ContaPoupanca cp1 = cpDAO.encontrarConta(new ContaPoupanca(5), cliDAO);
        
        ContaPoupancaDeposito cpd1 = new ContaPoupancaDeposito(cp1, new BigDecimal("300"), LocalDate.now(), LocalDate.now().plusDays(30));
    }

}
