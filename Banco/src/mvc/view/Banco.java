/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.time.LocalDate;
import java.time.Month;
import javax.swing.JOptionPane;
import mvc.model.Calendario;
import mvc.model.Cliente;
import mvc.model.ClienteDAO;
import mvc.model.ContaCorrente;
import mvc.model.ContaCorrenteCRUD;
import mvc.model.ContaCorrenteDAO;

/**
 *
 * @author leonardo
 */
public class Banco {
    // É o calendário que manipula a data do sistema
    Calendario calendario = new Calendario();
    // Manipula o vetor de contas
    public ContaCorrenteDAO ccDAO = new ContaCorrenteDAO(calendario.getData());
    // Manipula o vetor de clientes
    public ClienteDAO clienteDAO = new ClienteDAO();
    
    
    Banco(){
        int opcao;
        Cliente login;
        do{
            System.out.println("-- Tela de Login --");
            String cpfLogin = JOptionPane.showInputDialog("CPF: ");
            int senhaLogin = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));
          
            login = clienteDAO.buscar(new Cliente(null, cpfLogin, senhaLogin ));
            if( login != null){
                System.out.println("Usuário conectado com sucesso!");
                if(login.getSenha() == 12345){
                    menuAdm();
                }else{
                    menuCliente();
                }
            }else{
                System.out.println("Senha e/ou usuário incorreto(s).\n");
            }
        }while(login == null);
        
        
    }
    
    public static void main(String[] args) {
        new Banco();
    }
    
    public void menuCliente(){
        int opcao;
        
        StringBuilder menu = new StringBuilder("-- Menu do cliente --\n");
        menu.append("1. Conta Corrente").append("\n");
        menu.append("2. Conta Poupança").append("\n");
        menu.append("3. CDB").append("\n");
        menu.append("4. Fundos de investimento").append("\n");
        System.out.println(menu);
        opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre uma opção: "));

    }
    
     public void menuAdm(){
        int opcao;
        
        do{
        StringBuilder menu = new StringBuilder("-- Menu do adm --\n");
        menu.append("1. CRUD de clientes").append("\n");
        menu.append("2. CRUD de contas").append("\n");
        menu.append("3. CRUD de Investimentos").append("\n");
        menu.append("4. Avançar o dia").append("\n");
        System.out.println(menu);
        opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre uma opção: "));
        
        switch(opcao){
            case 1:
                
                break;
            case 2:
                ContaCorrenteCRUD ccCRUD = new ContaCorrenteCRUD(calendario.getData(), ccDAO, clienteDAO);
                break;
            case 3:
                break;
            case 4:
                break;
            default: System.out.println("Opção inválida.");
                break;
        }
        
        }while(opcao != 0);
    }
     
    
    
}
