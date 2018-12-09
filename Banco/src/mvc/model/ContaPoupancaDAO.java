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
public class ContaPoupancaDAO {

    private static final String INSERT = "insert into conta_poupanca" + "(idCliente, saldo)" + "values(?,?)";
    private static final String SELECT_ALL = "select * from conta_poupanca";
    private static final String UPDATE = "update conta_poupanca set idCliente = ?, saldo = ? where idConta_Poupanca = ?";
    private static final String SELECT_ONE = "select * from conta_poupanca where idConta_Poupanca = ?";
    private static final String SELECT_ONE_ID = "select * from conta_poupanca where idCliente = ?";
    private static final String DELETE = "delete from conta_poupanca where idConta_Poupanca = ?";

    public ContaPoupancaDAO() {

    }

    public void inserir(ContaPoupanca nova) {

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(INSERT)) {

            stmt.setLong(1, nova.getTitular().getId());
            stmt.setBigDecimal(2, nova.getSaldo());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Cadastrada com sucesso!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar! Erro: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Não foi possível cadastrar! Erro: " + e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public List<ContaPoupanca> listar(ClienteDAO clienteDAO) {
        List<ContaPoupanca> contas = new ArrayList();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT_ALL)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("idConta_Poupanca");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    Cliente cliente = new Cliente(idCliente);
                    cliente = clienteDAO.buscar(cliente);
                    ContaPoupanca conta = new ContaPoupanca(id, cliente, saldo);
                    contas.add(conta);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contas;
    }

    public ContaPoupanca encontrarConta(ContaPoupanca conta, ClienteDAO clienteDAO) {

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT_ONE)) {
            stmt.setLong(1, conta.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("idConta_Poupanca");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    Cliente cliente = new Cliente(idCliente);
                    cliente = clienteDAO.buscar(cliente);
                    conta = new ContaPoupanca(id, cliente, saldo);

                    return conta;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    
    public ContaPoupanca encontrarContaCliente(Cliente pesquisa, ClienteDAO clienteDAO) {

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(SELECT_ONE_ID)) {
            stmt.setLong(1, pesquisa.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    long id = rs.getLong("idConta_Poupanca");
                    long idCliente = rs.getLong("idCliente");
                    BigDecimal saldo = rs.getBigDecimal("saldo");
                    Cliente cliente = new Cliente(idCliente);
                    cliente = clienteDAO.buscar(cliente);
                    ContaPoupanca conta = new ContaPoupanca(id, cliente, saldo);

                    return conta;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void atualizar(ContaPoupanca conta) {

        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setLong(1, conta.getTitular().getId());
            stmt.setBigDecimal(2, conta.getSaldo());
            stmt.setLong(3, conta.getId());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Atualizada com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível atualizar!");
            throw new RuntimeException(e);
        }

    }

    public boolean deletar(ContaPoupanca conta) {

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setLong(1, conta.getId());

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
