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
public class ContaPoupancaCRUD {
    
    private ClienteDAO daoCliente;
    private LocalDate hoje;
    private ContaPoupancaDAO dao;
    
    public ContaPoupancaCRUD(LocalDate hoje, ContaPoupancaDAO dao, ClienteDAO daoCliente){
        this.dao = dao;
        this.daoCliente = daoCliente;
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
                    System.out.println("Lista de Contas Poupança");
                    this.dao.listar();
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
        StringBuilder menu = new StringBuilder("-- Conta Poupança --\n");
        menu.append("0. Sair").append("\n");
        menu.append("1. Inserir").append("\n");
        menu.append("2. Listar").append("\n");
        menu.append("3. Atualizar").append("\n");
        menu.append("4. Deletar").append("\n");
        menu.append("").append("\n");
        System.out.println(menu);
        int opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre uma das opções: "));
        return opcao;

    }
    
    public boolean inserir(){
        String cpfBusca = JOptionPane.showInputDialog("Entre com o cpf do titular");
        Cliente buscaCliente = daoCliente.buscar(new Cliente(null, cpfBusca, 0));
        if( buscaCliente != null){
            
            String limite = JOptionPane.showInputDialog("Entre com o limite inicial: R$");
            if(this.dao.inserir(new ContaPoupanca(buscaCliente, new BigDecimal(limite), hoje ))){
                return true;
            }
        }
            return false;
    }
    
    public void alterarDadosDaConta(){

        StringBuilder menu = new StringBuilder("-- Alterar Conta Poupança --\n");
        System.out.println(menu);
        
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Entre com o número da conta: "));
        ContaPoupanca buscaCC = new ContaPoupanca(numero);
        
        if(dao.encontrarConta(buscaCC) != null){
            BigDecimal quantia = new BigDecimal(JOptionPane.showInputDialog("Entre o novo saldo: "));
            buscaCC.setSaldo(quantia);
            BigDecimal limite = new BigDecimal(JOptionPane.showInputDialog("Entre com o novo limite: "));
            buscaCC.setLimite(limite);
            String cpfBusca = JOptionPane.showInputDialog("Entre com o cpf do novo titular");
            Cliente buscaCliente = daoCliente.buscar(new Cliente(null, cpfBusca, 0));
            
            if( buscaCliente != null){
                buscaCC.setTitular(buscaCliente);
                if(dao.atualizar(buscaCC)){
                    System.out.println("Conta Poupança atualizada com sucesso.");
                }else{
                        System.out.println("Não foi possível atualizar a conta.");
                    }        
            }else{
                System.out.println("Não foi possível encontrar o cliente.");
            }
  
    }else{
            System.out.println("Não foi possível encontrar a Conta Poupança.");
        }
 }
    public boolean excluir(){
        System.out.println("-- Excluir --\n");
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Entre com o número da conta: "));
        ContaPoupanca ccExcluir = new ContaPoupanca(numero);
        ccExcluir.setNumero(numero);
        return dao.deletar(ccExcluir);
    }

}
