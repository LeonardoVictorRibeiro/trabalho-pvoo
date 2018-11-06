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
 * @author leonardo
 */
public class CDBTeste {
    
    public static void main(String[] args) {
        CDBDAO cdbDAO = new CDBDAO();
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        ClienteDAO cliDAO = new ClienteDAO();
        
        
        CDB cdb2 = new CDB("EC");
        CDB cdb3 = new CDB("EM");
        CDB cdb4 = new CDB("EAD");
        
        /*
        cdbDAO.inserir(cdb2);
        cdbDAO.inserir(cdb3);
        cdbDAO.inserir(cdb4);
        */
        
        List<CDB> cdbs = cdbDAO.listar();
        
        for (CDB cdb : cdbs) {
            System.out.println(cdb);
        }

        /*
        // Busca por um CDB através do id
        System.out.println("\nCDB PESQUISADO");
        CDB cdbBusca3 = new CDB(3);
        
        cdbBusca3 = cdbDAO.buscar(cdbBusca3);
        
        System.out.println(cdbBusca3);
        */
        
       
        
        // Para investir no CBD são necessários 
        // CDBMovimento: CDB, Cliente, Valor, Uma Data Inicial e uma Data para o vencimento
        // ContaCorrente do Cliente 
        
         //Busca o cdb
        CDB cdbBusca4 = new CDB(4);
        cdbBusca4 = cdbDAO.buscar(cdbBusca4);
        
        //Busca o cliente
        Cliente clienteBusca4 = new Cliente(Long.parseLong("4"));
        clienteBusca4 = cliDAO.buscar(clienteBusca4);
        
        //ContaCorrente
        //ContaCorrente cc4 = ccdao.encontrarConta(conta, cliDAO);
        
        //Valor
        BigDecimal valor = new BigDecimal("1.000");
        
        //CDBMovimento
        CDBMovimento cdbMov1 = new CDBMovimento(cdbBusca4, clienteBusca4, valor, LocalDate.now(), LocalDate.now().plusDays(30));

        
        
        
    }
    
}
