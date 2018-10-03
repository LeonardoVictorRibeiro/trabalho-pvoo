/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import javax.swing.JOptionPane;
import mvc.model.Calendario;
import mvc.model.Cliente;
import mvc.model.ClienteCRUD;
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

    // Manipula o vetor de clientes
    public ClienteDAO clienteDAO = new ClienteDAO();

    // Manipula o vetor de contas
    public ContaCorrenteDAO ccDAO = new ContaCorrenteDAO(calendario.getData(), clienteDAO);
    private Cliente login;

    Banco() {
        int opcao;
        do {
            StringBuilder menuLogin = new StringBuilder("");
            menuLogin.append("1. Logar").append("\n");
            menuLogin.append("2. Fechar o programa").append("\n");
            System.out.println(menuLogin);
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre com a sua opção: "));

            switch (opcao) {
                case 1:
                    login();
                    break;

                default:
                    System.out.println("Programa encerrado.");
                    break;
            }

        } while (opcao == 1);

    }

    public void login() {
        do {
            System.out.println("-- Tela de Login --");
            String cpfLogin = JOptionPane.showInputDialog("CPF: ");
            int senhaLogin = Integer.parseInt(JOptionPane.showInputDialog("Senha: "));

            login = clienteDAO.buscar(new Cliente(null, cpfLogin, senhaLogin));
            if (login != null) {
                System.out.println("Usuário conectado com sucesso!");
                if (login.getSenha() == 12345) {
                    menuAdm();
                } else {
                    menuCliente();
                }
            } else {
                System.out.println("Senha e/ou usuário incorreto(s).\n");
            }
        } while (login == null);
    }

    public static void main(String[] args) {
        new Banco();
    }

    public void menuCliente() {
        int opcao;

        do {
            StringBuilder menu = new StringBuilder("-- Menu do cliente --\n");
            menu.append("1. Conta Corrente").append("\n");
            menu.append("2. Conta Poupança").append("\n");
            menu.append("3. CDB").append("\n");
            menu.append("4. Fundos de investimento").append("\n");
            System.out.println(menu);
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre uma opção: "));

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    operacoesContaCorrente();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        } while (opcao != 0);

    }

    public void menuAdm() {
        int opcao;

        do {
            StringBuilder menu = new StringBuilder("-- Menu do adm --").append(" Hoje é ").append(calendario.getData()).append("\n");
            menu.append("1. CRUD de clientes").append("\n");
            menu.append("2. CRUD de Conta Corrente").append("\n");
            menu.append("3. CRUD de Investimentos").append("\n");
            menu.append("4. Avançar o dia").append("\n");
            System.out.println(menu);
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre uma opção: "));

            switch (opcao) {
                case 0:
                    System.out.println("Deslogado.");
                    break;

                case 1:
                    ClienteCRUD clienteCRUD = new ClienteCRUD(clienteDAO);
                    break;
                case 2:
                    ContaCorrenteCRUD ccCRUD = new ContaCorrenteCRUD(calendario.getData(), ccDAO, clienteDAO);
                    break;
                case 3:
                    break;
                case 4:
                    alterarData();
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        } while (opcao != 0);
    }

    public void alterarData() {
        System.out.println("-- Avançar o dia --");
        long novaData = Long.parseLong(JOptionPane.showInputDialog("Entre com a quantidade de dias: "));
        calendario.setData(novaData);
    }

    private void operacoesContaCorrente() {
        int opcao;
        do {
            StringBuilder menu = new StringBuilder("-- Menu da Conta Corrente --\n");
            menu.append("1. Depositar\n");
            menu.append("2. Sacar\n");
            menu.append("3. consultar Saldo\n");
            menu.append("4. Extrato\n");
            System.out.println(menu);
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Entre com uma das opções: "));
            ContaCorrente contaCliente = ccDAO.procurarContaCliente(login);
            if (contaCliente != null) {
                switch (opcao) {
                    case 0:
                        System.out.println("Voltando ao menu principal do cliente.");
                        break;
                    case 1:
                        String valor = JOptionPane.showInputDialog("Digite o valor: R$");
                        BigDecimal bigValor = new BigDecimal(valor);
                        contaCliente.depositar(bigValor, calendario.getData());
                        break;
                    case 2:
                        String valorSacar = JOptionPane.showInputDialog("Digite o valor: R$");
                        BigDecimal bigValorSacar = new BigDecimal(valorSacar);
                        contaCliente.sacar(bigValorSacar, calendario.getData());
                        break;
                    case 3:
                        System.out.println("Saldo: R$" + contaCliente.getSaldo());
                        break;
                    case 4:
                        System.out.println(contaCliente.getExtrato());
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            } else {
                System.out.println("Você ainda não possui uma conta.");
            }
        } while (opcao != 0);
    }

}
