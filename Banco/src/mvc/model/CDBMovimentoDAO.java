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
public class CDBMovimentoDAO {
    
    private static final String LISTAR_ID = "select * from cdb_movimento where idCliente = ?";
    private static final String LISTAR_CDB = "select * from cdb_movimento where idCDB = ?";
    
    public List<CDBMovimento> listarID(Cliente c){
        
        ClienteDAO clienteDAO = new ClienteDAO();
        CDBDAO cdbDAO = new CDBDAO();
        List<CDBMovimento> movimentos = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(LISTAR_ID)){
            stmt.setLong(1, c.getId());
            
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    
                    long idMovimento = rs.getLong(1);
                    
                    long idCDB = rs.getLong(2);
                    //CDB cdb;
                    long idCliente = rs.getLong(3);
                    //Cliente cliente;
                    BigDecimal saldo = rs.getBigDecimal(4);
                    LocalDate dataInicio = rs.getDate(5).toLocalDate();
                    LocalDate dataFim = rs.getDate(6).toLocalDate();
                    boolean status = rs.getBoolean(7);
                    
                    Cliente cliente = clienteDAO.buscar( new Cliente(idCliente) );
                    
                    CDB cdb = cdbDAO.buscar( new CDB(idCDB) );
                    
                    CDBMovimento movimento = new CDBMovimento(idMovimento, cdb, cliente, saldo, dataInicio, dataInicio);
                    movimentos.add(movimento);
                    
                }
                return movimentos;
                
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public List<CDBMovimento> listarPorCDB(CDB cdb){
        List<CDBMovimento> movimentos = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(LISTAR_CDB)){
            stmt.setLong(1, cdb.getId());
            
            try (ResultSet rs = stmt.executeQuery()){
                
                while(rs.next()){
                    long idCDBMovimento = rs.getLong("idCDB_Movimento");
                    long idCDB = rs.getLong("idCDB");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                    LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();
                    boolean status = rs.getBoolean("status");
                    
                    
                    Cliente cliente = new ClienteDAO().buscar( new Cliente(idCliente));
                    
                    CDBMovimento movimento = new CDBMovimento(idCDBMovimento, cdb, cliente, saldo, dataInicio, dataTermino);
                    movimentos.add(movimento);
                }
                
                return movimentos;
                
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
  
}
