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
public class Fundo {
    
    private long id;
    private String nome;
    private BigDecimal saldototal;
    
    public Fundo(long id, String nome, BigDecimal saldoTotal){
        this.id = id;
        this.nome = nome;
        this.saldototal = saldoTotal;
    }
    
    public Fundo(String nome, BigDecimal saldoTotal){
        this.nome = nome;
        this.saldototal = saldoTotal;
    }
    
    public Fundo(long id){
        this.id = id;
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

    public BigDecimal getSaldototal() {
        return saldototal;
    }

    public void setSaldototal(BigDecimal saldototal) {
        this.saldototal = saldototal;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Fundo other = (Fundo) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fundo{" + "id=" + id + ", nome=" + nome + ", saldototal=" + saldototal + '}';
    }
    
    
    
}
