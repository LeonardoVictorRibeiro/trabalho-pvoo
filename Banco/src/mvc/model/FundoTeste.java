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
 * @author leonardo
 */
public class FundoTeste {
    
    public static void main(String[] args) {
        FundoDAO fundoDAO = new FundoDAO();
        
        /*
        // Cria fundos com 0 de saldoTotal
        Fundo f1 = new Fundo("FUNDO EASFODA", BigDecimal.ZERO);
        Fundo f2 = new Fundo("FUNDO ILOVEPOO", BigDecimal.ZERO);
        Fundo f3 = new Fundo("FUNDO ADSMINHAVIDAEVOCE", BigDecimal.ZERO);
        
        // Insere os fundos no banco
        fundoDAO.inserir(f1);
        fundoDAO.inserir(f2);
        fundoDAO.inserir(f3);
        */
        
        /*
        // Cria uma lista com os fundos recuperados do banco
        List<Fundo> fundos = fundoDAO.listar();
        
        for (Fundo fundo : fundos) {
            System.out.println(fundo);
        }
        */
        
        //Fundo criado para ser buscado no banco
        Fundo busca = new Fundo(Long.parseLong("3"));
        //Retorna a busca e salva na mesma variável
        busca = fundoDAO.buscar(busca);
        
        System.out.println(busca);
        
        
    }
    
}
