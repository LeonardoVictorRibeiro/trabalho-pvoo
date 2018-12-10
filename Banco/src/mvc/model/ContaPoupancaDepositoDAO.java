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
public class ContaPoupancaDepositoDAO {
    
    private static final String LISTAR_TUDO = "select * from conta_poupanca_deposito";
    private static final String LISTAR_POR_CONTA = "select * from conta_poupanca_deposito where idContaPoupanca = ?";
    private static final String ATUALIZAR_DEPOSITO = "update conta_poupanca_deposito set idContaPoupanca = ?, saldo = ?, dataInicio = ?, dataTermino = ?, aniversario = ?, status = ? where idDeposito = ?";
    
    
    public void atualizar(ContaPoupancaDeposito deposito){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(ATUALIZAR_DEPOSITO)){
            
            stmt.setLong(1, deposito.getConta().getId());
            stmt.setBigDecimal(2, deposito.getSaldo());
            stmt.setDate(3, Date.valueOf(deposito.getDataInicio()));
            stmt.setDate(4, Date.valueOf(deposito.getDataTermino()));
            stmt.setDate(5, Date.valueOf(deposito.getAniversario()));
            stmt.setBoolean(6, deposito.getStatusBoolean());
            stmt.setLong(7, deposito.getId());
            
            stmt.execute();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
    
    public List<ContaPoupancaDeposito> listarDepositosDeUmaConta(ContaPoupanca conta) {

        List<ContaPoupancaDeposito> depositos = new ArrayList();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(LISTAR_POR_CONTA)) {

            stmt.setLong(1, conta.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
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

    public List<ContaPoupancaDeposito> listar(ContaPoupancaDAO cpDAO, ClienteDAO clienteDAO) {
        List<ContaPoupancaDeposito> depositos = new ArrayList();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(LISTAR_TUDO)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long idDeposito = rs.getLong("idDeposito");
                    long idContaPoupanca = rs.getLong("idContaPoupanca");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    LocalDate dataInicio = rs.getDate("dataInicio").toLocalDate();
                    LocalDate dataTermino = rs.getDate("dataTermino").toLocalDate();
                    LocalDate aniversario = rs.getDate("aniversario").toLocalDate();
                    boolean status = rs.getBoolean("status");
                    //Cria a conta poupanca usando o id e busca a conta poupanca no banco 
                    ContaPoupanca conta = new ContaPoupanca(idContaPoupanca);
                    conta = cpDAO.encontrarConta(conta, clienteDAO);
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
