/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import mvc.controller.Login;
import mvc.model.Calendario;
import mvc.model.Cliente;

/**
 *
 * @author leonardo
 */
public class Programa {
    Calendario calendario = new Calendario();
    Login usuarioLogado = new Login();
    
    public static void main(String[] args) {
        new Programa().iniciar();
    }
    
    public void iniciar(){
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
    }
}
