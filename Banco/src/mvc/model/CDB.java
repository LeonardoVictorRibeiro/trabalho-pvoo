/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;

/**
 *
 * @author aluno
 */
public class CDB {
    
    private long id;
    private String nome;
    private BigDecimal saldoTotal = new BigDecimal("0.0");
    
    public CDB(long id){
        this.id = id;
    }
    public CDB(String nome){
        this.nome = nome;
        this.saldoTotal = new BigDecimal("0.0");
    }
    public CDB(long id, String nome, BigDecimal saldo){
        this.id = id;
        this.nome = nome;
        this.saldoTotal = saldo;
    }
    
    public void sacar(BigDecimal valor){
        this.saldoTotal = saldoTotal.subtract(valor);
    }
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldo) {
        this.saldoTotal = saldoTotal.add(saldo);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final CDB other = (CDB) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CDB{" + "id=" + id + ", nome=" + nome + ", saldoTotal=" + saldoTotal + '}';
    }
    
    
    
    
    
}
