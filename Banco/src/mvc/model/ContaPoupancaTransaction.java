/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mvc.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class ContaPoupancaTransaction {
    
    private static final String ATUALIZAR = "update conta_poupanca set saldo = ? where idConta_Poupanca = ?";
    private static final String INSERIR = "insert into conta_poupanca_deposito" + "(idContaPoupanca, saldo, dataInicio, dataTermino, aniversario, status)" + "values(?,?,?,?,?,?)";
    private static final String LISTAR_POR_CONTA = "select * from conta_poupanca_deposito where idContaPoupanca = ?";
    
    
    public void depositar(ContaPoupancaDeposito deposito){
        
        try (Connection connection = new ConnectionFactory().getConnection()){
            connection.setAutoCommit(false);
            
            try (PreparedStatement preparedUpdate = connection.prepareStatement(ATUALIZAR);
                    PreparedStatement preparedInsert = connection.prepareStatement(INSERIR)){
                preparedUpdate.setBigDecimal(1, deposito.getConta().getSaldo());
                preparedUpdate.setLong(2, deposito.getConta().getId());
                
                preparedUpdate.execute();
                
                preparedInsert.setLong(1, deposito.getConta().getId());
                preparedInsert.setBigDecimal(2, deposito.getSaldo());
                preparedInsert.setDate(3, Date.valueOf(deposito.getDataInicio()));
                preparedInsert.setDate(4, Date.valueOf(deposito.getDataTermino()));
                preparedInsert.setDate(5, Date.valueOf(deposito.getAniversario()));
                preparedInsert.setBoolean(6, true);
                
                preparedInsert.execute();
                
                
                connection.commit();
                
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
            
        } catch (SQLException f) {
            throw new RuntimeException(f);
        }
        
    }
    
    public List<ContaPoupancaDeposito> listarDepositosDeUmaConta(ContaPoupanca conta){
        
        List<ContaPoupancaDeposito> depositos = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(LISTAR_POR_CONTA)){
            
            stmt.setLong(1, conta.getId());
            
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    long idDeposito = rs.getLong("idDeposito");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                    LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();
                    LocalDate aniversario = rs.getDate("aniversario").toLocalDate();
                    boolean status = rs.getBoolean("status");
                    ContaPoupancaDeposito deposito = new ContaPoupancaDeposito(idDeposito, conta, saldo, dataInicio, dataTermino, aniversario, status);
                    depositos.add(deposito);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return depositos;
    }

}
