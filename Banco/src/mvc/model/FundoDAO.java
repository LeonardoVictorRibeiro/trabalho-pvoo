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

/**
 *
 * @author leonardo
 */
public class FundoDAO {
    
    private static final String INSERIR = "insert into fundo " + "(nome, saldoTotal)" + " values(?,?)";
    private static final String SELECT = "select * from fundo where idFundo = ?";
    private static final String SELECT_ALL = "select * from fundo";
    
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
}
