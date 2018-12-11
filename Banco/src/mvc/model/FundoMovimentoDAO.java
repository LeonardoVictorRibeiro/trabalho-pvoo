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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leonardo
 */
public class FundoMovimentoDAO {
    
    private static final String SELECT = "select * from fundo_movimento where idCliente = ?";
    
    public List<FundoMovimento> select(Cliente cliente){
        
        List<FundoMovimento> movimentos = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT)){
            stmt.setLong(1, cliente.getId());
            
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    long idFundoMovimento = rs.getLong("IdFundoMovimento");
                    long idFundo = rs.getLong("idFundo");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                    boolean status = rs.getBoolean("status");
                    
                    FundoMovimento movimento = new FundoMovimento();
                    movimento.setCliente(cliente);
                    movimento.setFundo( new Fundo(idFundo));
                    movimento.setDatainicio(dataInicio);
                    movimento.setSaldo(saldo);
                    movimento.setStatus(status);
                    
                    movimentos.add(movimento);
                }
                
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
        
        return movimentos;
    }
    
}
