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
public class CDBTransaction {

    private static final String UPDATE_CDB = "update cdb set saldoTotal = ? where idCDB = ?";
    private static final String UPDATE_CORRENTE = "update conta_corrente set saldo = ? where idContaCorrente = ?";
    private static final String INSERIR_MOV_COR = "insert into movimento_conta" + "(idContaCorrente, tipo_movimento, descricao, valor, data)" + "values(?,?,?,?,?)";
    private static final String INSERT_MOV = "insert indo cdb_movimento" + "(idCDB, idCliente, saldo, dataInicio, dataTermino, status)" + "values(?,?,?,?,?,?)";

    public boolean inserir(CDB cdb, ContaCorrente contaCorrente, CDBMovimento movimento, MovimentoContaCorrente movCorrente) {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            
            connection.setAutoCommit(false);
            
            try (PreparedStatement updateCDB = connection.prepareStatement(UPDATE_CDB);
                    PreparedStatement updateCorrente = connection.prepareStatement(UPDATE_CORRENTE);
                    PreparedStatement insertMovCorrente = connection.prepareStatement(INSERIR_MOV_COR);
                    PreparedStatement insertMov = connection.prepareStatement(INSERT_MOV)) {

                
                updateCDB.setBigDecimal(1, cdb.getSaldoTotal());
                updateCDB.setLong(2, cdb.getId());
                updateCDB.execute();
                
                updateCorrente.setBigDecimal(1, contaCorrente.getSaldo());
                updateCorrente.setLong(2, contaCorrente.getId());
                updateCorrente.execute();
                
                insertMovCorrente.setLong(1, contaCorrente.getId());
                insertMovCorrente.setInt(2, movCorrente.getTipo());
                insertMovCorrente.setString(3, movCorrente.getDescricao());
                insertMovCorrente.setBigDecimal(4, movCorrente.getValor());
                insertMovCorrente.setDate(5, Date.valueOf(movCorrente.getData()));
                
                insertMovCorrente.execute();
                
                insertMov.setLong(1, movimento.getCdb().getId());
                insertMov.setLong(2, movimento.getCliente().getId());
                insertMov.setBigDecimal(3, movimento.getSaldo());
                insertMov.setDate(4, Date.valueOf(movimento.getDataInicio()));
                insertMov.setDate(5, Date.valueOf(movimento.getDataTermino()));
                insertMov.setBoolean(6, movimento.getStatus());
                insertMov.execute();
                
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    public boolean inserir2(CDB cdb, ContaCorrente contaCorrente, CDBMovimento movimento, MovimentoContaCorrente movCorrente) {

        try (Connection connection = new ConnectionFactory().getConnection()) {
            
            connection.setAutoCommit(false);
            
            try (PreparedStatement updateCDB = connection.prepareStatement(UPDATE_CDB);
                    PreparedStatement updateCorrente = connection.prepareStatement(UPDATE_CORRENTE);
                    PreparedStatement insertMovCorrente = connection.prepareStatement(INSERIR_MOV_COR);
                    PreparedStatement insertMov = connection.prepareStatement(INSERT_MOV)) {

                
                updateCDB.setBigDecimal(1, cdb.getSaldoTotal());
                updateCDB.setLong(2, cdb.getId());
                updateCDB.execute();
                
                updateCorrente.setBigDecimal(1, contaCorrente.getSaldo());
                updateCorrente.setLong(2, contaCorrente.getId());
                updateCorrente.execute();
                
                insertMovCorrente.setLong(1, contaCorrente.getId());
                insertMovCorrente.setInt(2, movCorrente.getTipo());
                insertMovCorrente.setString(3, movCorrente.getDescricao());
                insertMovCorrente.setBigDecimal(4, movCorrente.getValor());
                insertMovCorrente.setDate(5, Date.valueOf(movCorrente.getData()));
                
                insertMovCorrente.execute();
                
                insertMov.setLong(1, movimento.getCdb().getId());
                insertMov.setLong(2, movimento.getCliente().getId());
                insertMov.setBigDecimal(3, movimento.getSaldo());
                insertMov.setDate(4, Date.valueOf(movimento.getDataInicio()));
                insertMov.setDate(5, Date.valueOf(movimento.getDataTermino()));
                insertMov.setBoolean(6, movimento.getStatus());
                insertMov.execute();
                
                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
