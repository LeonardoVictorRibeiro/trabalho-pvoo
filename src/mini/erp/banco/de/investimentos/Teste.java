/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

/**
 *
 * @author Leonardo
 */
public class Teste {
    
    public static void main(String[] args) {
        Cliente c1 = new Cliente("Leonardo", "123456732");
        Cliente c2 = new Cliente("Victor", "232312313");
        Cliente c3 = new Cliente("Ribeiro", "678904534");
        
        System.out.println(c1.getId());
        System.out.println(c2.getId());
        System.out.println(c3.getId());
        
        System.out.println(c1.equals(c2));
        System.out.println(c2.equals(c2));
    }
}
