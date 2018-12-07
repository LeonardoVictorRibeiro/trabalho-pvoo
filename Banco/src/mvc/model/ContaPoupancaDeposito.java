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
public class ContaPoupancaDeposito {

    private long id;
    private ContaPoupanca conta;
    private BigDecimal saldo;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private LocalDate aniversario;
    private boolean status;

    public ContaPoupancaDeposito(ContaPoupanca conta, BigDecimal saldo, LocalDate dataInicio, LocalDate dataTermino) {
        this.conta = conta;
        this.saldo = saldo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.aniversario = dataInicio;
        this.status = true;
    }
    
     public ContaPoupancaDeposito(long id, ContaPoupanca conta, BigDecimal saldo, LocalDate dataInicio, LocalDate dataTermino, LocalDate aniversario, boolean status) {
         this.id = id;
        this.conta = conta;
        this.saldo = saldo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.aniversario = aniversario;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ContaPoupanca getConta() {
        return conta;
    }

    public void setConta(ContaPoupanca conta) {
        this.conta = conta;
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

    public LocalDate getAniversario() {
        return aniversario;
    }

    public void setAniversario(LocalDate aniversario) {
        this.aniversario = aniversario;
    }

    public String getStatus() {
        if(this.status == true){
            return "Ativo";
        }else{
            return "Vencido";
        }
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final ContaPoupancaDeposito other = (ContaPoupancaDeposito) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ContaPoupancaDeposito{" + "id=" + id + ", conta=" + conta + ", saldo=" + saldo + ", dataInicio=" + dataInicio + ", dataTermino=" + dataTermino + ", aniversario=" + aniversario + ", status=" + status + '}';
    }
    
    

}
