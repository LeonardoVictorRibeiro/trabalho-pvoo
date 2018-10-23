/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Leonardo
 */
public class Cliente {
    private long id;
    private String nome;
    private String cpf;
    private LocalDate dataNasc;
    private int senha;

    public Cliente(String nome, String cpf, LocalDate dataNasc, int senha){
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.senha = senha;
    }
    
    public Cliente(Long id, String nome, String cpf, LocalDate dataNasc, int senha){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.senha = senha;
    }
    
    public Cliente(Long id){
        this.id = id;
    }
    public Cliente(String nome, String cpf, int senha){
        
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }
    
    public Cliente(String cpf){
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", dataNasc=" + dataNasc + ", senha=" + senha + '}';
    }
    
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.cpf);
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        return true;
    }
    
    
    
}
