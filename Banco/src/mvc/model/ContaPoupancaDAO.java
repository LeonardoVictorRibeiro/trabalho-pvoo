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
 * @author leonardo
 */
public class ContaPoupancaDAO {
    ContaPoupanca[] dao = new ContaPoupanca[50];
    private LocalDate hoje;
    
    public ContaPoupancaDAO(ClienteDAO clienteDAO, LocalDate hoje){
        this.hoje = hoje;
        Cliente cliente2 = clienteDAO.buscar(new Cliente(null, "222222", 0));
        Cliente cliente3 = clienteDAO.buscar(new Cliente(null, "333333", 0));
        Cliente cliente4 = clienteDAO.buscar(new Cliente(null, "444444", 0));
        Cliente cliente5 = clienteDAO.buscar(new Cliente(null, "555555", 0));
        
        ContaPoupanca cp1 = new ContaPoupanca(cliente2, new BigDecimal("0"), this.hoje);
        ContaPoupanca cp2 = new ContaPoupanca(cliente3, new BigDecimal("0"), this.hoje);
        ContaPoupanca cp3 = new ContaPoupanca(cliente4, new BigDecimal("0"), this.hoje);
        ContaPoupanca cp4 = new ContaPoupanca(cliente5, new BigDecimal("0"), this.hoje);
        
        
        this.inserir(cp1);
        this.inserir(cp2);
        this.inserir(cp3);
        this.inserir(cp4);
        
        
    }

     public boolean inserir(ContaPoupanca cp){
        int pos = posicaoLivre();
        if(pos != -1){
            dao[pos] = cp;
            return true;
        }
        return false;
    }
    
    public int posicaoLivre(){
        int pos;
        for( pos  = 0; dao[pos] != null  && pos < dao.length;  pos++){}
        if(dao[pos] != null){
            return -1;
        }
        return pos;
    }
    
    public void listar(){
        for( int i = 0; i < dao.length; i++){
            if(dao[i] != null){
                System.out.println(dao[i]);
            }
        }
    }
    
    public ContaPoupanca encontrarConta(ContaPoupanca cp){
        for(int i = 0; i < dao.length; i++){
            if(dao[i] != null && dao[i].equals(cp)){
               return dao[i];                
            }
        }
        return null;
    }
    
    public boolean atualizar(ContaPoupanca cp){
        ContaPoupanca posicao = encontrarConta(cp);
        if(posicao != null){
            if(cp.getSaldo() != new BigDecimal("-1")){
                posicao.setSaldo(cp.getSaldo());
            }
            if(cp.getLimite() != new BigDecimal("-1")){
                posicao.setLimite(cp.getLimite());
            }
            if(cp.getTitular() != null){
                posicao.setTitular(cp.getTitular());
            }
            return true;
        }
        return false;
    }
    public boolean deletar(ContaPoupanca cp){
        for( int i = 0; dao[i] != null && i < dao.length; i++){
            if(dao[i].equals(cp)){
                dao[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public ContaPoupanca procurarContaCliente(Cliente c){
        for (ContaPoupanca contaPoupanca : dao) {
            if(contaPoupanca != null && contaPoupanca.getTitular().equals(c)){
                return contaPoupanca;
            }
        }
        return null;
    }
}
