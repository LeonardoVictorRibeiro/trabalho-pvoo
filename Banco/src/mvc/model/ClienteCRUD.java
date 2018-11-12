/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import javax.swing.JOptionPane;

/**
 *
 * @author leonardo
 */
public class ClienteCRUD {
    private ClienteDAO clienteDAO;

    public ClienteCRUD(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
        menuPrincipal();
    }

    public boolean inserir() {

        String nome = JOptionPane.showInputDialog("Nome: ");
        String cpf = JOptionPane.showInputDialog("CPF: ");
        int senha = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));

        return true;
                //clienteDAO.inserir(new Cliente(nome, cpf, senha));
    }
    
    public boolean atualizar(){
        
        String cpf = JOptionPane.showInputDialog("Entre com o cpf do cliente: ");
        Cliente alterar = clienteDAO.buscar(new Cliente(null, cpf, 0));
        if(alterar != null){
            String novoCPF = JOptionPane.showInputDialog("Entre com o novo cpf: ");
            String novoNome = JOptionPane.showInputDialog("Entre com o novo nome: ");
            int novaSenha = Integer.parseInt(JOptionPane.showInputDialog("Entre com o nova senha: "));
            return clienteDAO.atualizar(alterar, new Cliente(novoNome, novoCPF, novaSenha));
             
        }
        
        return false;
    }
    
    public boolean excluir(){
        String buscaCPF = JOptionPane.showInputDialog("Digite o cpf do cliente: ");
        
        return clienteDAO.deletar(new Cliente(null, buscaCPF, 0));
    }

    public void menuPrincipal() {
        int opcao;

        do {
            StringBuilder menu = new StringBuilder("-- Cliente CRUD ---\n");
            menu.append("0. Sair").append("\n");
            menu.append("1. Inserir").append("\n");
            menu.append("2. Listar").append("\n");
            menu.append("3. Atualizar").append("\n");
            menu.append("4. Deletar").append("\n");
            System.out.println(menu);
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite a opção escolhida: "));
            
            switch(opcao){
                case 0:
                    System.out.println("Voltando ao menu principal do adm");
                    break;
                case 1:
                    if(inserir()){
                        System.out.println("Cliente inserido com sucesso.");
                    }else{
                        System.out.println("Não foi possível inserir este cliente.");
                    }
                    break;
                case 2:
                    System.out.println(clienteDAO.listar());
                    break;
                case 3:
                    if(atualizar()){
                        System.out.println("Dados do cliente alterados com sucesso!");
                    }else{
                        System.out.println("Falha ao alterar dados do cliente");
                    }
                    break;
                case 4:
                    if(excluir()){
                        System.out.println("Cliente excluído com sucesso!");
                    }else{
                        System.out.println("Não foi possível excluir o cliente.");
                    }
                    break;
                default: System.out.println("Opção inválida.");
                    break;
                
            }
            
        }while(opcao != 0);
        
    
    }
}
