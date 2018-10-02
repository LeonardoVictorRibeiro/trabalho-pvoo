/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

/**
 *
 * @author leonardo
 */
public class ClienteDAO {
    private Cliente[] clientes = new Cliente[50];
    
    public ClienteDAO(){
        Cliente c1 = new Cliente("Leonardo", "111111", 12345);
        Cliente c2 = new Cliente("Pedro", "222222", 12342);
        Cliente c3 = new Cliente("Caio", "333333", 12343);
        Cliente c4 = new Cliente("Ana", "444444", 12344);
        Cliente c5 = new Cliente("Paula", "555555", 12346);
        
        this.inserir(c1);
        this.inserir(c2);
        this.inserir(c3);
        this.inserir(c4);
        this.inserir(c5);

       
    } 
    
    public static void main(String[] args) {
        new ClienteDAO();
    }
    
    public boolean inserir(Cliente novo){
        for(int i = 0; i < clientes.length; i++){
            if(clientes[i] == null){
                clientes[i] = novo;
                return true;
            }
        }
        return false;
    }
    
    public StringBuilder listar(){
        StringBuilder listar = new StringBuilder("\n");
        for (Cliente cliente : clientes) {
            if(cliente != null){
                listar.append(cliente.toString());
            }
        }
        return listar;
    }
            
    public boolean atualizar(Cliente alterar, Cliente novosDados){
        for (Cliente cliente : clientes) {
            if(cliente != null && cliente.equals(alterar)){
                cliente.setNome(novosDados.getNome());
                cliente.setCpf(novosDados.getCpf());
                cliente.setSenha(novosDados.getSenha());
                return true;
            }
        }
        return false;
    } 
            
    public boolean deletar(Cliente compara){
        for(int i = 0; i < clientes.length; i++){
            if(clientes[i] != null && clientes[i].equals(compara)){
                clientes[i] = null;
                return true;
            }
        }
        return false;
    }
    
    public Cliente buscar(Cliente busca){
        for( int i = 0; i < clientes.length; i++){
            if(clientes[i] != null && clientes[i].equals(busca)){
                return clientes[i];
            }
        }
        return null;
    }
}
