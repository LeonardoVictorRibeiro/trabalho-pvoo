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
 * @author Leonardo
 */
public class ContaPoupanca {

    private long id;
    private BigDecimal saldo = new BigDecimal("0");
    private Cliente titular;

    public ContaPoupanca(long id, Cliente titular, BigDecimal valor) {
        this.id = id;
        this.titular = titular;
        this.saldo = saldo.add(valor);
    }

    public ContaPoupanca(Cliente titular, BigDecimal valor) {
        this.titular = titular;
        this.saldo = saldo.add(valor);
    }

    public ContaPoupanca(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean setSaldo(BigDecimal quantia) {
        this.saldo = quantia;
        return true;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void depositar(BigDecimal valor) {
        this.saldo = saldo.add(valor);
    }


    public void sacar(BigDecimal valor) {

        this.saldo = saldo.subtract(valor);
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final ContaPoupanca other = (ContaPoupanca) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContaPoupanca{" + "id=" + id + ", saldo=" + saldo + ", titular=" + titular + '}';
    }
    
    

}
