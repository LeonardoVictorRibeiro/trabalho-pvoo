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
        CDBTransaction cdbTransaction = new CDBTransaction();
        ContaCorrenteDAO ccdao = new ContaCorrenteDAO();
        ClienteDAO cliDAO = new ClienteDAO();
        
        /*
        CDB cdb2 = new CDB("EC");
        CDB cdb3 = new CDB("EM");
        CDB cdb4 = new CDB("EAD");
        */
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
        ContaCorrente cc4 = ccdao.encontrarConta(clienteBusca4, cliDAO);
        
        /*
        System.out.println(clienteBusca4);
        System.out.println(cc4);
        */
        
        //Valor que será depositado no CDB e subtraido da conta corrente
        BigDecimal valor = new BigDecimal("1000.00");
        
        cc4.sacar(valor);
        
        //Atualiza o movimento na conta corrente. Ex: No dia de 01/02/2018 foi depositado R$100,00 na conta 1
        MovimentoContaCorrente movimento1 = new MovimentoContaCorrente(cc4, 0, "Investimento CDB", valor, LocalDate.now());
        
        //Atualiza o saldo no CDB
        cdbBusca4.setSaldoTotal(valor);
        
        //CDBMovimento
        CDBMovimento cdbMov1 = new CDBMovimento(cdbBusca4, clienteBusca4, valor, LocalDate.now(), LocalDate.now().plusDays(30));
        
        //CDBTransaction é quem realiza a atualização em todas as tabelas relacionadas
        cdbTransaction.atualizar(cdbBusca4, cc4, cdbMov1, movimento1);
        
        

        
        
        
    }
    
}
