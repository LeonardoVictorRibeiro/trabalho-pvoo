/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author leonardo
 */
public class ClienteDAO {
    private static final String INSERIR = "insert into clientes" + "(nome, cpf, dataNasc, senha)" + "values(?,?,?,?)";
    private static final String SELECIONAR_TODOS = "select * from clientes";
    private static final String DELETAR = "delete from clientes where idCliente = ?";
    private static final String SELECIONAR_UM = "select * from clientes where idCliente = ?";
    private static final String SELECIONAR_POR_CPF = "select * from clientes where cpf = ?";
    private static final String UPDATE = "update clientes set nome = ?, cpf = ?, dataNasc = ?, senha = ? where idCliente = ?";

    public void inserir(Cliente novo) {

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(INSERIR)) {

            stmt.setString(1, novo.getNome());
            stmt.setString(2, novo.getCpf());
            stmt.setDate(3, Date.valueOf(novo.getDataNasc()));
            stmt.setInt(4, novo.getSenha());

            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar.");
            throw new RuntimeException(e);
        }

    }

    public List<Cliente> listar() {
 
        List<Cliente> clientes = new ArrayList();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECIONAR_TODOS)){
            
           try(ResultSet rs = stmt.executeQuery()){
               while(rs.next()){
                   long id = rs.getLong("idCliente");
                   String nome = rs.getString("nome");
                   String cpf = rs.getString("cpf");
                   LocalDate dataNasc = rs.getDate("dataNasc").toLocalDate();
                   int senha = rs.getInt("senha");
                   
                   Cliente cliente = new Cliente(id, nome, cpf, dataNasc, senha);
                   
                   clientes.add(cliente);
               }
           }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        } 

        return clientes;
    }
            
    
    public Cliente atualizar(Cliente clienteASerAlterado){
        
        try(Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
                stmt.setString(1, clienteASerAlterado.getNome());
                stmt.setString(2, clienteASerAlterado.getCpf());
                stmt.setDate(3, Date.valueOf(clienteASerAlterado.getDataNasc()));
                stmt.setInt(4, clienteASerAlterado.getSenha());
                stmt.setLong(5, clienteASerAlterado.getId());
                
                stmt.execute();
                
                JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar!");
            throw new RuntimeException(e);
        }
        
        return clienteASerAlterado;
    }
    

    public boolean deletar(Cliente clienteASerExcluido) {
        
        
        try(Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(DELETAR)){
            stmt.setLong(1, clienteASerExcluido.getId());
            
            stmt.execute();
            
        JOptionPane.showMessageDialog(null, "Excluido com sucesso!");   
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar!");
            throw new RuntimeException(e);
        }
        
       
        return false;
    }

    public Cliente buscar(Cliente busca) {
        
        try(Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECIONAR_UM)){
            stmt.setLong(1, busca.getId());
            
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    long id = rs.getLong("idCliente");
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    LocalDate dataNasc = rs.getDate("dataNasc").toLocalDate();
                    int senha = rs.getInt("senha");
                    
                    Cliente cliente = new Cliente(id, nome, cpf, dataNasc, senha);
                    return cliente;
                }
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    public Cliente buscarPorCPF(Cliente c){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECIONAR_POR_CPF)){
            stmt.setString(1, c.getCpf());
            
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    long id = rs.getLong("idCliente");
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    LocalDate dataNasc = rs.getDate("dataNasc").toLocalDate();
                    int senha = rs.getInt("senha");
                    
                    Cliente cliente = new Cliente(id, nome, cpf, dataNasc, senha);
                    return cliente;
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
        return null;
    }
}
