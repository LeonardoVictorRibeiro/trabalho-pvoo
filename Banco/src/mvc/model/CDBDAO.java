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
public class CDBDAO {
    
    private static final String INSERIR = "insert into cdb" + "(nome, saldoTotal)" + "values(?,?)";
    private static final String LISTAR = "select * from cdb";
    private static final String SELECT = "select * from cdb where idCDB = ?";
    private static final String DELETAR = "delete from cdb where idCDB = ?";
    private static final String ALTERAR = "update cdb set nome = ?, saldoTotal = ? where idCDB = ?";
    
    public boolean inserir(CDB novo){
        
        try( Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(INSERIR)) {
            stmt.setString(1, novo.getNome());
            stmt.setBigDecimal(2, BigDecimal.ZERO);
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
       
    }
    
    public List<CDB> listar(){
        List<CDB> cdbs = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(LISTAR)){
            
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    long id = rs.getLong("idCDB");
                    String nome = rs.getString("nome");
                    BigDecimal saldo = rs.getBigDecimal("saldoTotal");
                    CDB cdb = new CDB(id, nome, saldo);
                    cdbs.add(cdb);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);    
        }
        
        return cdbs;
        
    }
    
    public CDB buscar(CDB cdb){
        
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT)){
            stmt.setLong(1, cdb.getId());
            
            try(ResultSet rs = stmt.executeQuery()) {
                
                while(rs.next()){
                    long id = rs.getLong("idCDB");
                    String nome = rs.getString("nome");
                    BigDecimal saldo = rs.getBigDecimal("saldoTotal");
                    
                    cdb.setId(id);
                    cdb.setNome(nome);
                    cdb.setSaldoTotal(saldo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cdb;
    }
    
    public void deletar(CDB cdb){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(DELETAR)){
            
            stmt.setLong(1, cdb.getId());
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public void atualizar(CDB cdb){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(ALTERAR)){
            
            stmt.setString(1, cdb.getNome());
            stmt.setBigDecimal(2, cdb.getSaldoTotal());
            stmt.setLong(3, cdb.getId());
            
            stmt.execute();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar!");
            throw new RuntimeException(e);
        }
        
        
    }
    
}
