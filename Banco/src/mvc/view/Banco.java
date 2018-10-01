/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.time.LocalDate;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteDAO;

/**
 *
 * @author leonardo
 */
public class Banco {
    public LocalDate hoje = LocalDate.now();
    public ContaCorrenteDAO ccDAO;
    
    Banco(){
        ccDAO = new ContaCorrenteDAO(hoje); 
    }
    
    public static void main(String[] args) {
        new Banco();
    }
    
    
}
