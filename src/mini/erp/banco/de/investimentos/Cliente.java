/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mini.erp.banco.de.investimentos;

import java.util.Objects;

/**
 *
 * @author Leonardo
 */
public class Cliente extends Usuario{
    private static long serial;
    
    
    Cliente(String nome, String cpf){
        super.id= ++serial;
        super.setNome(nome);
        super.setCpf(cpf);
    }

    Cliente(String cpf){
        super.setCpf(cpf);
    }    
    
}
