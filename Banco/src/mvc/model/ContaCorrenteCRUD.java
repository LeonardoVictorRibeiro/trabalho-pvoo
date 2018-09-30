/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author leonardo
 */
public class ContaCorrenteCRUD {
    
    ContaCorrenteDAO dao = new ContaCorrenteDAO();
    LocalDate hoje = LocalDate.now();
    Cliente cliente = new Cliente("Leonardo", "1111111", 1234567);
    
    public ContaCorrenteCRUD(){
        int opcao;
        do{
            opcao = menuPrincipal();
            
            switch(opcao){
                case 0:
                    System.out.println("Retornando ao menu principal");
                    break;
                case 1:
                    if(inserir()){
                        System.out.println("Conta criada com sucesso!");
                    }else{
                        System.out.println("Falha ao criar conta!");
                    }
                    break;
                case 2:
                    System.out.println("Lista de Contas Corrente");
                    dao.listar();
                    break;
                case 3:

                    break;
                case 4:
                    break;
                default:
                    break;
             
            }
        }while(opcao != 0);
        
        
    }
    
    public static void main(String[] args) {
        new ContaCorrenteCRUD();
    }
    
    public int menuPrincipal(){
        StringBuilder menu = new StringBuilder("Conta Corrente\n");
        menu.append("0. Sair").append("\n");
        menu.append("1. Inserir").append("\n");
        menu.append("2. Listar").append("\n");
        menu.append("3. Atualizar").append("\n");
        menu.append("4. Deletar").append("\n");
        menu.append("").append("\n");
        System.out.println(menu);
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre com a sua opção: "));
        return opcao;

    }
    
    public boolean inserir(){
        String valor = JOptionPane.showInputDialog("Entre com o deposito inicial: R$");
        String limite = JOptionPane.showInputDialog("Entre com o limite inicial: R$");
        ContaCorrente novaCC = new ContaCorrente(cliente, new BigDecimal(valor), new BigDecimal(limite), hoje );
        if(dao.inserir(novaCC)){
            return true;
        }
            return false;
    }
    
    public int alterarDadosDaConta(){
        StringBuilder menu = new StringBuilder("Alterar Conta Corrente\n");
        System.out.println(menu);
        
        long numero = Long.parseLong(JOptionPane.showInputDialog("Entre com o número da conta: R$"));
        String saldo = JOptionPane.showInputDialog("Entre com saldo: R$");
        BigDecimal saldoBig = new BigDecimal(saldo);
        
        ContaCorrente novaCC = new ContaCorrente(null, null, null, null);
        //novaCC.setNumero(numero);
        return -1;
    }

    
}
