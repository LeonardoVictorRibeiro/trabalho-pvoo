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
 * @author Leonardo Victor Ribeiro <leonardovictor@outlook.com>
 */
public class MovimentoContaCorrente {
    private long id;
    private ContaCorrente conta;
    private int tipo;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    
    public MovimentoContaCorrente(long id, ContaCorrente conta, int tipo, String descricao, BigDecimal valor, LocalDate data){
        this.id = id;
        this.conta = conta;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }
    public MovimentoContaCorrente(ContaCorrente conta, int tipo, String descricao, BigDecimal valor, LocalDate data){
        this.conta = conta;
        this.tipo = tipo;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContaCorrente getConta() {
        return conta;
    }

    public void setConta(ContaCorrente conta) {
        this.conta = conta;
    }

    public int getTipo() {
        return tipo;
    }
    

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final MovimentoContaCorrente other = (MovimentoContaCorrente) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "MovimentoContaCorrente{" + "id=" + id + ", conta=" + conta + ", tipo=" + tipo + ", descricao=" + descricao + ", valor=" + valor + ", data=" + data + '}';
    }
    
    

}
