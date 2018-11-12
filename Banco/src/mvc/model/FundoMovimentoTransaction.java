/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author leonardo
 */
public class FundoMovimentoTransaction {
    
    private static final String INSERT_FUNDO_MOVIMENTACAO = "insert into fundo_movimento" + "(idFundo, idCliente, saldo, dataInicio, status)" + " values(?,?,?,?,?)";
    private static final String UPDATE_FUNDO = "update fundo set saldo = ? where idFundo = ?";
    private static final String UPDATE_CONTA = "update conta_corrente set saldo = ? where idContaCorrente = ?";
    private static final String INSERT_CONTA_MOVIMENTACAO = "insert into movimento_conta" + "(idContaCorrente, tipo_movimento, descricao, valor, data)" + "values(?,?,?,?,?)";
    
    
    public void inserir(FundoMovimento movimento, MovimentoContaCorrente movimentoConta){
        
        try (Connection connection = new ConnectionFactory().getConnection()){
            connection.setAutoCommit(false);
            
            try (PreparedStatement contaCorrenteUpdate = connection.prepareStatement(UPDATE_CONTA);
                    PreparedStatement contaMovimentacaoInsert = connection.prepareStatement(INSERT_CONTA_MOVIMENTACAO);
                    PreparedStatement fundoUpdate = connection.prepareStatement(UPDATE_FUNDO);
                    PreparedStatement fundoMovimentacaoInsert = connection.prepareStatement(INSERT_FUNDO_MOVIMENTACAO)){
                
                
                contaCorrenteUpdate.setBigDecimal(1, movimentoConta.getConta().getSaldo());
                contaCorrenteUpdate.setLong(2, movimentoConta.getConta().getId());
                contaCorrenteUpdate.execute();
                
                contaMovimentacaoInsert.setLong(1, movimentoConta.getConta().getId());
                contaMovimentacaoInsert.setInt(2, movimentoConta.getTipo());
                contaMovimentacaoInsert.setString(3, movimentoConta.getDescricao());
                contaMovimentacaoInsert.setBigDecimal(4, movimentoConta.getValor());
                contaMovimentacaoInsert.setDate(4, Date.valueOf(movimentoConta.getData()));
                contaMovimentacaoInsert.execute();
                
                fundoUpdate.setBigDecimal(1, movimento.getFundo().getSaldototal());
                fundoUpdate.setLong(2, movimento.getFundo().getId());
                fundoUpdate.execute();
                
                fundoMovimentacaoInsert.setLong(1, movimento.getFundo().getId());
                fundoMovimentacaoInsert.setLong(2, movimento.getCliente().getId());
                fundoMovimentacaoInsert.setBigDecimal(3, movimento.getSaldo());
                fundoMovimentacaoInsert.setDate(4, Date.valueOf(movimento.getDatainicio()));
                fundoMovimentacaoInsert.setBoolean(5, movimento.isStatus());
                fundoMovimentacaoInsert.execute();
                
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }
    
}
