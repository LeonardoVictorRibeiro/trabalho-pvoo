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
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class ContaPoupancaTransaction {

    private static final String ATUALIZAR_POUPANCA = "update conta_poupanca set saldo = ? where idConta_Poupanca = ?";
    private static final String ATUALIZAR_CORRENTE = "update conta_corrente set saldo = ? where idContaCorrente = ?";
    private static final String ATUALIZAR_DEPOSITO = "update conta_poupanca_deposito set status = ? where idDeposito = ?";
    private static final String INSERIR = "insert into conta_poupanca_deposito" + "(idContaPoupanca, saldo, dataInicio, dataTermino, aniversario, status)" + "values(?,?,?,?,?,?)";
    private static final String INSERIR_MOVIMENTACAO = "insert into movimento_conta" + "(idContaCorrente, tipo_movimento, descricao, valor, data)" + "values(?,?,?,?,?)";
    

    public void depositar(ContaPoupancaDeposito deposito, ContaCorrente contaCorrente, MovimentoContaCorrente movimento) {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement poupancaUPDATE = connection.prepareStatement(ATUALIZAR_POUPANCA);
                    PreparedStatement correnteUPDATE = connection.prepareStatement(ATUALIZAR_CORRENTE);
                    PreparedStatement preparedInsert = connection.prepareStatement(INSERIR);
                    PreparedStatement movimentacaoInsert = connection.prepareStatement(INSERIR_MOVIMENTACAO)) {
                
                // Atualiza o saldo na conta corrente
                correnteUPDATE.setBigDecimal(1, contaCorrente.getSaldo());
                correnteUPDATE.setLong(2, contaCorrente.getId());
                correnteUPDATE.execute();
                
                // Insere uma nova movimentação da conta corrente
                movimentacaoInsert.setLong(1, contaCorrente.getId());
                movimentacaoInsert.setInt(2, movimento.getTipo());
                movimentacaoInsert.setString(3, movimento.getDescricao());
                movimentacaoInsert.setBigDecimal(4, movimento.getValor());
                movimentacaoInsert.setDate(5, Date.valueOf(movimento.getData()));
                movimentacaoInsert.execute();
                
                // Atualiza o saldo na conta poupança
                poupancaUPDATE.setBigDecimal(1, deposito.getConta().getSaldo());
                poupancaUPDATE.setLong(2, deposito.getConta().getId());
                poupancaUPDATE.execute();
                
                // Insere um novo registro de depósito na conta poupanca
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

    

    public void resgatar(ContaPoupanca contaPoupanca, ClienteDAO clienteDAO, ContaCorrente contaCorrente, ContaPoupancaDeposito deposito) {

        try (Connection connection = new ConnectionFactory().getConnection()){
            connection.setAutoCommit(false);
            
        try (PreparedStatement correnteUPDATE = connection.prepareStatement(ATUALIZAR_CORRENTE);
                PreparedStatement poupancaUPDATE = connection.prepareStatement(ATUALIZAR_POUPANCA);
                    PreparedStatement depositoUPDATE = connection.prepareStatement(ATUALIZAR_DEPOSITO)) {

                correnteUPDATE.setBigDecimal(1, contaCorrente.getSaldo());
                correnteUPDATE.setLong(2, contaCorrente.getId());
                
                correnteUPDATE.execute();
                
                poupancaUPDATE.setBigDecimal(1, contaPoupanca.getSaldo());
                poupancaUPDATE.setLong(2, contaPoupanca.getId());
                
                poupancaUPDATE.execute();
                
                depositoUPDATE.setBoolean(1, false);
                depositoUPDATE.setLong(2, deposito.getId());
                
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
