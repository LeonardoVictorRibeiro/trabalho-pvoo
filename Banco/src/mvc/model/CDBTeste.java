/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.List;

/**
 *
 * @author leonardo
 */
public class CDBTeste {
    
    public static void main(String[] args) {
        CDBDAO cdbDAO = new CDBDAO();
        
        
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
        
        
        // Busca por um CDB através do id
        System.out.println("\nCDB PESQUISADO");
        CDB cdbBusca3 = new CDB(3);
        
        cdbBusca3 = cdbDAO.buscar(cdbBusca3);
        
        System.out.println(cdbBusca3);
        
    }
    
}
