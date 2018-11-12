/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author aluno
 */
public class CDBMovimento {

private long id;
private CDB cdb;
private Cliente cliente;
private BigDecimal saldo;
private LocalDate dataInicio;
private LocalDate dataTermino;
private boolean status;

    public CDBMovimento(CDB cdb, Cliente cliente, BigDecimal saldo, LocalDate dataInicio,LocalDate dataTermino) {
        this.cdb = cdb;
        this.cliente = cliente;
        this.saldo = saldo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.status = true;
        
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CDB getCdb() {
        return cdb;
    }

    public void setCdb(CDB cdb) {
        this.cdb = cdb;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CDBMovimento other = (CDBMovimento) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CDBMovimento{" + "id=" + id + ", cdb=" + cdb + ", cliente=" + cliente + ", saldo=" + saldo + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", status=" + status + '}';
    }

    
    
}
