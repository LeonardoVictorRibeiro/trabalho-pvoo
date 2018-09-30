/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

/**
 *
 * @author Leonardo
 */
public class ContaCorrenteDAO {
    private ContaCorrente[] dao  = new  ContaCorrente[50];
    
    public ContaCorrenteDAO(){
        
    }
    
    
    public boolean inserir(ContaCorrente cc){
        int pos = posicaoLivre();
        if(pos != -1){
            dao[pos] = cc;
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
    
    public boolean listar(){
        for( int i = 0; i < dao.length; i++){
            if(dao[i] != null){
                System.out.println(dao[i]);
            }
        }
        return  false;
    }
    
    public int encontrarConta(long id){
        
        for(int i = 0; i < dao.length; i++){
            if(dao[i] != null && dao[i].equals(i)){
                return i;
            }
        }

        return -1;
    }
    
    public boolean atualizar(long id){
        for(int i = 0; i < dao.length; i++){

        }
        return false;
    }
    public boolean deletar(){
        return false;
    }
    
}
