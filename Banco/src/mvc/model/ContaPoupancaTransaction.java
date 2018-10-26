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

    private static final String ATUALIZAR_POUPANCA = "update conta_poupanca set saldo = ? where idConta_Poupanca = ?";
    private static final String ATUALIZAR_CORRENTE = "update conta_corrente set saldo = ? where idContaCorrente = ?";
    private static final String ATUALIZAR_DEPOSITO = "update conta_poupanca_deposito set status = ? where idDeposito = ?";
    private static final String INSERIR = "insert into conta_poupanca_deposito" + "(idContaPoupanca, saldo, dataInicio, dataTermino, aniversario, status)" + "values(?,?,?,?,?,?)";
    private static final String LISTAR_POR_CONTA = "select * from conta_poupanca_deposito where idContaPoupanca = ?";
    private static final String LISTAR_TUDO = "select * from conta_poupanca_deposito";

    public void depositar(ContaPoupancaDeposito deposito, ContaCorrente contaCorrente) {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement poupancaUPDATE = connection.prepareStatement(ATUALIZAR_POUPANCA);
                    PreparedStatement correnteUPDATE = connection.prepareStatement(ATUALIZAR_CORRENTE);
                    PreparedStatement preparedInsert = connection.prepareStatement(INSERIR)) {

                correnteUPDATE.setBigDecimal(1, contaCorrente.getSaldo());
                correnteUPDATE.setLong(2, contaCorrente.getId());

                correnteUPDATE.execute();

                poupancaUPDATE.setBigDecimal(1, deposito.getConta().getSaldo());
                poupancaUPDATE.setLong(2, deposito.getConta().getId());

                poupancaUPDATE.execute();

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

    public void resgatar(ContaPoupanca contaPoupanca, ClienteDAO clienteDAO, ContaCorrente contaCorrente, ContaPoupancaDeposito deposito) {

        try (Connection connection = new ConnectionFactory().getConnection()){
            connection.setAutoCommit(false);
            
        try (PreparedStatement correnteUPDATE = connection.prepareStatement(ATUALIZAR_CORRENTE);
                PreparedStatement poupancaUPDATE = connection.prepareStatement(ATUALIZAR_POUPANCA);
                    PreparedStatement depositoUPDATE = connection.prepareStatement(INSERIR)) {

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
