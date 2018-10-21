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
 * 
 */
public class TestaContaCorrente {
    
    public static void main(String[] args) {
        
        
        ClienteDAO clidao = new ClienteDAO();
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        
        Cliente c1 = clidao.buscar(new Cliente(Long.parseLong("1")));
        Cliente c2 = clidao.buscar(new Cliente(Long.parseLong("2")));
        Cliente c3 = clidao.buscar(new Cliente(Long.parseLong("3")));
        
        ContaCorrente cc1 = new ContaCorrente(c1, new BigDecimal("1000"));
        ContaCorrente cc2 = new ContaCorrente(c2, new BigDecimal("2000"));
        ContaCorrente cc3 = new ContaCorrente(c3, new BigDecimal("3000"));
        /*
        ccdao.inserir(cc1);
        ccdao.inserir(cc2);
        ccdao.inserir(cc3);
        */
        
         System.out.println(ccdao.listar(clidao));
         //cc3.setId(3);
         //System.out.println(ccdao.encontrarConta(cc3, clidao));
         
         //System.out.println(ccdao.deletar(new ContaCorrente(4)));
         
         ccdao.atualizar(new ContaCorrente(2, null,new BigDecimal("3000")));
        System.out.println(ccdao.listar(clidao));
        
        
    }

}
