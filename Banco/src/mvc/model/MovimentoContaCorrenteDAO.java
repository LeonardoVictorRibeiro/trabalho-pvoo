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
public class MovimentoContaCorrenteDAO {
    
    private static final String INSERIR = "insert into movimento_conta" + "(idContaCorrente, tipo_movimento, descricao, valor, data)" + "values(?,?,?,?,?)";
    private static final String SELECT_ALL = "select * from movimento_conta";
    private static final String SELECT = "select * from movimento_conta where idContaCorrente = ?";
    
    public List<MovimentoContaCorrente> listarMovimentosDeTodasContas(ContaCorrenteDAO contaCDAO, ClienteDAO clienteDAO){
        
        List<MovimentoContaCorrente> movimentacoes = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedSelect = connection.prepareStatement(SELECT_ALL)){
            try(ResultSet rs = preparedSelect.executeQuery()){
                while(rs.next()){
                    int idMovimentacao = rs.getInt("idMovimento_Conta");
                    long idContaCorrente = rs.getLong("idContaCorrente");
                    int tipoMovimento = rs.getInt("tipo_movimento");
                    String descricao = rs.getString("descricao");
                    BigDecimal valor = rs.getBigDecimal("valor");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    ContaCorrente contaEncontrada = new ContaCorrente(idContaCorrente);
                    contaEncontrada = contaCDAO.encontrarConta(contaEncontrada, clienteDAO);
                    
                    MovimentoContaCorrente movimento = new MovimentoContaCorrente(idMovimentacao, contaEncontrada, tipoMovimento, descricao, valor, data);
                    movimentacoes.add(movimento);
                }
                
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return movimentacoes;
        
    }
    
    public List<MovimentoContaCorrente> listarMovimentosDeUmaConta(ContaCorrente conta){
        List<MovimentoContaCorrente> movimentacoes = new ArrayList();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedSelect = connection.prepareStatement(SELECT)){
            preparedSelect.setLong(1, conta.getId());
            
            try(ResultSet rs = preparedSelect.executeQuery()){
                while(rs.next()){
                    long idMovimentacao = rs.getLong("idMovimento_Conta");
                    long idContaCorrente = rs.getLong("idContaCorrente");
                    int tipo = rs.getInt("tipo_movimento");
                    String descricao = rs.getString("descricao");
                    BigDecimal valor = rs.getBigDecimal("valor");
                    LocalDate data = rs.getDate("data").toLocalDate();
                    
                    MovimentoContaCorrente movimento = new MovimentoContaCorrente(idMovimentacao, conta, tipo, descricao, valor, data);
                    movimentacoes.add(movimento);
                }
                
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return movimentacoes;
    }
    
    public void inserir(ContaCorrente conta, MovimentoContaCorrente movimento){
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement preparedInsert = connection.prepareStatement(INSERIR)){
            preparedInsert.setLong(1, conta.getId());
            preparedInsert.setInt(2, movimento.getTipo());
            preparedInsert.setString(3, movimento.getDescricao());
            preparedInsert.setBigDecimal(4, movimento.getValor());
            preparedInsert.setDate(5, Date.valueOf(movimento.getData()));
            
            preparedInsert.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    

}
