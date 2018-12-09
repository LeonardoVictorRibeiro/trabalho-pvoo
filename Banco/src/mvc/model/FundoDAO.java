/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author leonardo
 */
public class FundoDAO {
    
    private static final String INSERIR = "insert into fundo " + "(nome, saldoTotal)" + " values(?,?)";
    private static final String SELECT = "select * from fundo where idFundo = ?";
    private static final String SELECT_ALL = "select * from fundo";
    private static final String DELETAR = "delete from fundo where idFundo = ?";
    private static final String ATUALIZAR = "update fundo set nome = ?, saldoTotal = ? where idFundo = ? ";
    
    public void inserir(Fundo fundo){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(INSERIR)){
            stmt.setString(1, fundo.getNome());
            stmt.setBigDecimal(2, fundo.getSaldototal());
            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public List<Fundo> listar(){
        
        List<Fundo> fundos = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT_ALL)){
            
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    long id = rs.getLong("idFundo");
                    String nome = rs.getString("nome");
                    BigDecimal saldoTotal = rs.getBigDecimal("saldoTotal");
                    Fundo fundo = new Fundo(id, nome, saldoTotal);
                    fundos.add(fundo);
                }
                
            }            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return fundos;
        
    }
    
    
    public Fundo buscar(Fundo fundo){
        
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT)){
            stmt.setLong(1, fundo.getId());
            
            
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    long id = rs.getLong("idFundo");
                    String nome = rs.getString("nome");
                    BigDecimal saldoTotal = rs.getBigDecimal("saldoTotal");
                    Fundo encontrado = new Fundo(id, nome, saldoTotal);
                    return encontrado;
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return null;
    }
    
    
    public void excluir(Fundo f){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(DELETAR)){
            
            stmt.setLong(1, f.getId());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível excluir. Erro: " + e);
            throw new RuntimeException(e);
        }
        
        
    }
    
    public void atualizar( Fundo f){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(ATUALIZAR)){
            stmt.setString(1, f.getNome());
            stmt.setBigDecimal(2, f.getSaldototal());
            stmt.setLong(3, f.getId());
            
            stmt.execute();
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar!");
            throw new RuntimeException(e);
        }
        
    }
}
