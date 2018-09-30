/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.Objects;

/**
 *
 * @author Leonardo
 */
public class Cliente {
    private static long serial;
    private long id;
    private String nome;
    private String cpf;
    private int senha;

    public Cliente(String nome, String cpf, int senha){
        this.id = serial++;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
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

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("\n");
        info.append("Id: ").append(id).append("\n");
        info.append("Nome: ").append(nome).append("\n");
        info.append("Cpf: ").append(cpf).append("\n");
        info.append("Senha: ").append(senha).append("\n");
        return info.toString();
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
