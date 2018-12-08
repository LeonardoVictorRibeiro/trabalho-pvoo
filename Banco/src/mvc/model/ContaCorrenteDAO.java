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
import javax.swing.JOptionPane;

/**
 *
 * @author Leonardo
 */
public class ContaCorrenteDAO {
    
    private static final String INSERT = "insert into conta_corrente" + "(saldo, idCliente)" + "values(?,?)";
    private static final String SELECT_ALL = "select * from conta_corrente";
    private static final String UPDATE = "update conta_corrente set idCliente = ?, saldo = ? where idContaCorrente = ?";
    private static final String SELECT_ONE = "select * from conta_corrente where idContaCorrente = ?";
    private static final String SELECT_ONE_ID = "select * from conta_corrente where idCliente = ?";
    private static final String DELETE = "delete from conta_corrente where idContaCorrente = ?";


    public ContaCorrenteDAO() {
        

    }

    public boolean inserir(ContaCorrente nova) {
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(INSERT)){
            stmt.setBigDecimal(1, nova.getSaldo());
            stmt.setLong(2, nova.getTitular().getId());
            
            stmt.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);  
        }
        
    }
    

    public List<ContaCorrente> listar(ClienteDAO clienteDAO) {
        List<ContaCorrente> contas = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT_ALL)){
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    long id = rs.getLong("idContaCorrente");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    Cliente cliente = new Cliente(idCliente);
                    cliente = clienteDAO.buscar(cliente);
                    ContaCorrente conta = new ContaCorrente(rs.getLong("idContaCorrente"), cliente, saldo);
                    contas.add(conta);
                }
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return contas;
    }
    

    public ContaCorrente encontrarConta(ContaCorrente conta, ClienteDAO clienteDAO) {
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT_ONE)){
            stmt.setLong(1, conta.getId());
            
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    long id = rs.getLong("idContaCorrente");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    Cliente cliente = new Cliente(idCliente);
                    cliente = clienteDAO.buscar(cliente);
                    conta = new ContaCorrente(id, cliente, saldo);
                    
                    return conta;
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
 
        return null;
    }
    
    public ContaCorrente encontrarConta(Cliente procura, ClienteDAO clienteDAO) {
        

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT_ONE_ID)){
            stmt.setLong(1, procura.getId());
            
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    long id = rs.getLong("idContaCorrente");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    ContaCorrente conta = new ContaCorrente(id, procura, saldo);
                    
                    return conta;
                }
            } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Usuário ou conta não encontrados. Erro: " + e );
            throw new RuntimeException(e);
        }
 
        return null;
    }
    
    public boolean atualizar(ContaCorrente conta) {
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(UPDATE)){
            stmt.setLong(1, conta.getTitular().getId());
            stmt.setBigDecimal(2, conta.getSaldo());
            stmt.setLong(3, conta.getId());
            
            stmt.execute();
            return true;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
   

    public boolean deletar(ContaCorrente conta) {
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(DELETE)){
            stmt.setLong(1, conta.getId());
            
            stmt.execute();
            return true;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
