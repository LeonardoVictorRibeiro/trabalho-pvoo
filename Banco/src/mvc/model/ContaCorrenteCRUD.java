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
    
    ContaCorrenteDAO dao;
    LocalDate hoje;
    Cliente cliente = new Cliente("Leonardo", "1111111", 1234567);
    
    public ContaCorrenteCRUD(LocalDate hoje){
        this.hoje = hoje;
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
                    alterarDadosDaConta();
                    break;
                case 4:
                    if(excluir()){
                        System.out.println("Conta excluída com sucesso.");
                    }else{
                        System.out.println("Não foi possível excluir a conta.");
                    }
                    break;
                default:
                    break;
             
            }
        }while(opcao != 0);
        
        
    }
    
    public int menuPrincipal(){
        StringBuilder menu = new StringBuilder("-- Conta Corrente --\n");
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
    
    public void alterarDadosDaConta(){

        StringBuilder menu = new StringBuilder("Alterar Conta Corrente\n");
        System.out.println(menu);
        
        long numero = Long.parseLong(JOptionPane.showInputDialog("Entre com o número da conta: "));
        ContaCorrente buscaCC = new ContaCorrente(null , new BigDecimal(-1), new BigDecimal(-1), null);
        buscaCC.setNumero(numero);
        
        int opcao;
        
        if(dao.encontrarConta(buscaCC) != null){
           do{
            
            StringBuilder subMenu = new StringBuilder("\n");
            subMenu.append("0. Voltar").append("\n");
            subMenu.append("1. Saldo").append("\n");
            subMenu.append("2. Limite").append("\n");
            subMenu.append("3. Titular").append("\n");
            System.out.println(subMenu);
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção desejada: "));
            
            switch(opcao){
                case 0:
                    System.out.println("Retornando ao menu principal...");
                    break;
                case 1:
                    BigDecimal quantia = new BigDecimal(JOptionPane.showInputDialog("Entre o novo saldo: "));
                    buscaCC.setSaldo(quantia);
                    if(dao.atualizar(buscaCC)){
                        System.out.println("Saldo atualizado com sucesso.");
                    }else{
                        System.out.println("Não foi possível atualizar o saldo.");
                    }
                    break;
                case 2:
                    BigDecimal limite = new BigDecimal(JOptionPane.showInputDialog("Entre com o novo limite: "));
                    buscaCC.setSaldo(limite);
                    if(dao.atualizar(buscaCC)){
                        System.out.println("Limite atualizado com sucesso.");
                    }else{
                        System.out.println("Não foi possível atualizar o limite.");
                    }
                    break;
                case 3:
                    //Lembrar de procurar clientes
                    buscaCC.setTitular(cliente);
                    if(dao.atualizar(buscaCC)){
                        System.out.println("Limite atualizado com sucesso.");
                    }else{
                        System.out.println("Não foi possível atualizar o limite.");
                    }
                    break;
                default: System.out.println("Opção inválida.");
                    break;
            }
           }while(opcao != 0);
            
        }
  
    }
    
    public boolean excluir(){
        System.out.println("-- Excluir --\n");
        long numero = Long.parseLong(JOptionPane.showInputDialog("Entre com o número da conta: "));
        ContaCorrente ccExcluir = new ContaCorrente(null, new BigDecimal("0"), new BigDecimal("0"), null);
        ccExcluir.setNumero(numero);
        return dao.deletar(ccExcluir);
    }

    
}
